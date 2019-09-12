

/* *
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites or repositories,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */
package cop5556fa19;


import static cop5556fa19.Token.Kind.*;


import java.io.IOException;
import java.io.Reader;
import java.util.*;


public class Scanner {
	
	Reader r;
	int CurrLine=0;
	int CurrPos=0;
	Map <String, Token.Kind> keywords = new HashMap<>();
	
	private enum State {
		START, HAS_EQ, IN_NUMLIT, IN_IDENT, IN_DIGIT, HAS_COLON, HAS_DIV, HAS_GT, HAS_LT, HAS_NOT, HAS_DOT, HAS_DOTDOT
		}

	int ch;
	

	
	@SuppressWarnings("serial")
	public static class LexicalException extends Exception {	
		public LexicalException(String arg0) {
			super(arg0);
		}
	}
	
	public Scanner(Reader r) throws IOException {
		this.r = r;
		ch = r.read();
		this.keywords.put("and", Token.Kind.KW_and);
		this.keywords.put("while", Token.Kind.KW_while);
		this.keywords.put("until", Token.Kind.KW_until);
		this.keywords.put("true", Token.Kind.KW_true);
		this.keywords.put("then", Token.Kind.KW_then);
		this.keywords.put("return", Token.Kind.KW_return);
		this.keywords.put("repeat", Token.Kind.KW_repeat);
		this.keywords.put("or", Token.Kind.KW_or);
		this.keywords.put("not", Token.Kind.KW_not);
		this.keywords.put("nil", Token.Kind.KW_nil);
		this.keywords.put("local", Token.Kind.KW_local);
		this.keywords.put("in", Token.Kind.KW_in);
		this.keywords.put("if", Token.Kind.KW_if);
		this.keywords.put("goto", Token.Kind.KW_goto);
		this.keywords.put("function", Token.Kind.KW_function);
		this.keywords.put("for", Token.Kind.KW_for);
		this.keywords.put("false", Token.Kind.KW_false);
		this.keywords.put("elseif", Token.Kind.KW_elseif);
		this.keywords.put("else", Token.Kind.KW_else);
		this.keywords.put("do", Token.Kind.KW_do);
		this.keywords.put("break", Token.Kind.KW_break);
		this.keywords.put("end", Token.Kind.KW_end);
		
	}
	
	
	void getChar() throws IOException {
		  //read next char
		  ch= r.read();
		  //update currPos and currLine
		  CurrPos++;
		  CurrLine++;
		}


	public Token getNext() throws Exception {
		
		  //ch = r.read();    
		  Token t = null;
		  StringBuilder sb; //for token text
		  int pos=-1;
		  int line=-1;
		  State state = State.START;
		  while (t==null)
		  {
		    switch (state)
		    {
		      case START: { 
		    	  
		    	  // skip white space
		    	    pos = CurrPos;
		    	    switch (ch) 
		    	    {
		    	    	case '+': {
		    	    		
		    	    		t = new Token(OP_PLUS, "+", pos, line);
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case '*': {
		    	    		
		    	    		t = new Token(OP_TIMES, "*", pos, line);
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case '=': {
		    	    	
		    	    		pos = CurrPos;
		    	    		state = State.HAS_EQ; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '0': {
		    	    		
		    	    		t = new Token(INTLIT,"0",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case ',': {
		    	    		
		    	    		
		    	    		t = new Token(COMMA,",",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case ';': {
		    	    		
		    	    		
		    	    		t = new Token(SEMI,";",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case ']': {
		    	    		
		    	    		t = new Token(RSQUARE,"]",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '[': {
		    	    		
		    	    		
		    	    		t = new Token(LSQUARE,"[",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '}': {
		    	    		
		    	    		
		    	    		t = new Token(RCURLY,"}",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	
		    	    	case '{': {
		    	    		
		    	    		t = new Token(LCURLY,"{",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '(': {
		    	    		
		    	    		t = new Token(LPAREN,"(",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case ')': {
		    	    		
		    	    		t = new Token(RPAREN,")",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '|': {
		    	    		
		    	    		t = new Token(BIT_OR,"|",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '~': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_NOT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '&': {
		    	    		
		    	    		t = new Token(BIT_AMP,"&",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '#': {
		    	    		
		    	    		t = new Token(OP_HASH,"#",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '^': {
		    	    		
		    	    		t = new Token(OP_POW,"^",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '%': {
		    	    		
		    	    		t = new Token(OP_MOD,"%",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '/': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_DIV; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '-': {
		    	    		
		    	    		t = new Token(OP_MINUS,"-",pos,line);
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	
		    	    	case ':': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_COLON; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '.': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_DOT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '>': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_GT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '<': {
		    	    		
		    	    		pos = CurrPos;
		    	    		state = State.HAS_LT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case  -1: {
		    	    		
		    	    		CurrPos++;
		    	    		t = new Token(EOF, "EOF", pos, line);
		    	    		break;
		    	    	}
		    	    	
		    	    	default: {
		    	    		
		    	            if (Character.isDigit(ch))
		    	            {
		    	            	pos = CurrPos;
		    	            	state = State.IN_DIGIT; 		

		    	            } 
		    	            else if (Character.isJavaIdentifierStart(ch)) 
		    	            {
		    	            	 pos = CurrPos;
		    	                 state = State.IN_IDENT; 
		    	                 
		    	             } 
		    	             else 
		    	             { 
		    	            	 //error(�.);
		    	            	 System.out.println("Error!!!!!!!!");
		    	             }
		    	          }
		    	
		    	      } // switch (ch)

		    	  
		      } break;
		      
		      case HAS_COLON: 
		      {
		    	  
		    	  if ((char)ch == ':')
		    	  {
		    		  t = new Token(COLONCOLON,"::",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(COLON,":",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      
		      case HAS_DOT: 
		      {
		    	  
		    	  if ((char)ch == '.')
		    	  {
		    		  pos = CurrPos;
	    	    	  state = State.HAS_DOTDOT; 
	    	    	  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(DOT,".",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      case HAS_DOTDOT: 
		      {
		    	  
		    	  if ((char)ch == '.')
		    	  {
		    		  t = new Token(DOTDOTDOT,"...",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(DOTDOT,"..",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      case HAS_GT: 
		      {
		    	  
		    	  if ((char)ch == '>')
		    	  {
		    		  t = new Token(BIT_SHIFTR,">>",pos,line);
		    		  getChar();
		    	  }
		    	  if ((char)ch == '=')
		    	  {
		    		  t = new Token(REL_GE,">=",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(REL_GT,">",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      case HAS_LT: 
		      {
		    	  
		    	  if ((char)ch == '<')
		    	  {
		    		  t = new Token(BIT_SHIFTL,"<<",pos,line);
		    		  getChar();
		    	  }
		    	  if ((char)ch == '=')
		    	  {
		    		  t = new Token(REL_LE,"<=",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(REL_LT,">",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      
		      
		      case HAS_EQ: 
		      {
		    	  if ((char)ch == '=')
		    	  {
		    		  t = new Token(REL_EQEQ,"==",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
//		    		  state = State.START;
		    		  t = new Token(ASSIGN,"=",pos,line);
		    	  }
		    	  
		      } break;
		      
		      case HAS_NOT: 
		      {
		    	  if ((char)ch == '=')
		    	  {
		    		  t = new Token(REL_NOTEQ,"~=",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(BIT_XOR,"~",pos,line);
		    	  }
		    	  
		      } break;
		      
		      case HAS_DIV: 
		      {
		    	  if ((char)ch == '/')
		    	  {
		    		  t = new Token(OP_DIVDIV,"//",pos,line);
		    		  getChar();
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(OP_DIV,"/",pos,line);
		    	  }
		    	  
		      } break;
		      
		      case IN_DIGIT: 
		      {
		    	  sb = new StringBuilder();
		    	  while (Character.isDigit(ch))
  	              {
  	                  sb.append((char)ch);
  	                  getChar();
  	              } 
		    	  t = new Token(INTLIT,sb.toString(),pos,line);		    	  
		    	  
		      } break;
		      
		      case IN_IDENT: 
		      {
		    	  	 sb = new StringBuilder();
		    	  	 
		    	  	 
	                 sb.append((char)ch);
	                 getChar();	    	  
		    	  
		      } break;
		      
		      default:
		      {
		    		  System.out.println("Errorr!!!");
		    		  
		      }
		      
		    }//switch(state)
		    
		    
		  }//while
		  
		  
		return t;
			//throw new LexicalException("Useful error message");
		}

}



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
		START, HAS_EQ, IN_NUMLIT, IN_IDENT, IN_DIGIT, HAS_COLON, HAS_DIV, HAS_GT, HAS_LT, HAS_NOT, HAS_DOT, HAS_DOTDOT, HAS_DQUOTES, HAS_SQUOTES, HAS_HYPHEN
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
		  //update currPos
		  CurrPos++;
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
		    	    line = CurrLine;
		    	    switch (ch) 
		    	    {
		    	    	case '+': {
		    	    		
		    	    		t = new Token(OP_PLUS, "+", pos, line);
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case '\n': {
		    	    		
		    	    		CurrLine++;
		    	    		//t = new Token(OP_PLUS, "+", pos, line);
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case ' ': {
		    	    		
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case '*': {
		    	    		
		    	    		t = new Token(OP_TIMES, "*", pos, line);
		    	    		getChar();
		    	    	
		    	    	}break;
		    	    	
		    	    	case '=': {
		    	    	
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
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
		    	    	
		    	    	case '"': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_DQUOTES; 
		    	    		getChar();
		    	    	
		    	    		
		    	    	}break;
		    	    	
		    	    	case '\'': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_SQUOTES; 
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
		    	    		line = CurrLine;
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
		    	    		line = CurrLine;
		    	    		state = State.HAS_DIV; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '-': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_HYPHEN; 
		    	    		getChar();
		    
		    	    		
		    	    	}break;
		    	    	
		    	    	
		    	    	
		    	    	case ':': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_COLON; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '.': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_DOT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '>': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
		    	    		state = State.HAS_GT; 
		    	    		getChar();
		    	    		
		    	    	}break;
		    	    	
		    	    	case '<': {
		    	    		
		    	    		pos = CurrPos;
		    	    		line = CurrLine;
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
		    	            	line = CurrLine;
		    	            	state = State.IN_DIGIT; 		

		    	            } 
		    	            else if (Character.isJavaIdentifierStart(ch)) 
		    	            {
		    	            	 pos = CurrPos;
		    	            	 line = CurrLine;
		    	                 state = State.IN_IDENT; 
		    	                 
		    	             } 
		    	             else 
		    	             { 
		    	            	 //error(….);
		    	            	 throw new LexicalException("Invalid Character!!");
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
		      
		      case HAS_HYPHEN: 
		      {
		    	  
		    	  if ((char)ch == '-')
		    	  {
		    		  while((char)ch != '\n')
		    		  {
		    			  getChar();
		    		  }
		    		  state = state.START;
		    	  }
		    	  else 
		    	  {
		    		  t = new Token(OP_MINUS,"-",pos,line);
		    	  }
		    	  
		    	  
		      } break;
		      
		      
		      case HAS_DOT: 
		      {
		    	  
		    	  if ((char)ch == '.')
		    	  {
		    		  pos = CurrPos;
		    		  line = CurrLine;
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
		      
		      case HAS_DQUOTES: 
		      {
		    	  sb = new StringBuilder();
		    	  sb.append('"');
		    	  int flag =0;
		    	  while((char)ch != '"')
		    	  {
		    		  flag = 0;
		    		  if (ch == '\\')
		    		  {
		    			  flag =1;
		    			  getChar();
		    		  }
		    		  if (ch == 'n' && flag ==1)
		    		  {
		    			  sb.append((char)10);
	  	                  getChar();
		    		  }
		    		  if (ch == 'a' && flag ==1)
		    		  {
		    			  sb.append((char)7);
	  	                  getChar();
		    		  }
		    		  if (ch == 'b' && flag ==1)
		    		  {
		    			  sb.append((char)8);
	  	                  getChar();
		    		  }
		    		  if (ch == 'f' && flag ==1)
		    		  {
		    			  sb.append((char)12);
	  	                  getChar();
		    		  }
		    		  if (ch == 'r' && flag ==1)
		    		  {
		    			  sb.append((char)13);
	  	                  getChar();
		    		  }
		    		  if (ch == 't' && flag ==1)
		    		  {
		    			  sb.append((char)9);
	  	                  getChar();
		    		  }
		    		  if (ch == 'v' && flag ==1)
		    		  {
		    			  sb.append((char)11);
	  	                  getChar();
		    		  }
		    		  if (ch == '\\' && flag ==1)
		    		  {
		    			  sb.append((char)92);
	  	                  getChar();
		    		  }
		    		  if (ch == '"' && flag ==1)
		    		  {
		    			  sb.append((char)34);
	  	                  getChar();
		    		  }
		    		  if (ch == '\'' && flag ==1)
		    		  {
		    			  sb.append((char)39);
	  	                  getChar();
		    		  }
		    		  
		    		  if(flag == 0)
		    		  {
		    			  sb.append((char)ch);
	  	                  getChar();
	  	                  if (ch == -1)
	  	                  {
	  	                	throw new LexicalException("String missing double quote!");
	  	                  }
		    		  }
		    	  }
		    	  sb.append('"');
		    	  t = new Token(STRINGLIT,sb.toString(),pos,line);
		    	  getChar();
		    	  
		      } break;
		      
		      case HAS_SQUOTES: 
		      {
		    	  sb = new StringBuilder();
		    	  sb.append("'");
		    	  int flag = 0;
		    	  while((char)ch != '\'')
		    	  {
		    		  flag = 0;
		    		  if (ch == '\\')
		    		  {
		    			  flag =1;
		    			  getChar();
		    		  }
		    		  if (ch == 'n' && flag ==1)
		    		  {
		    			  sb.append((char)10);
	  	                  getChar();
		    		  }
		    		  if (ch == 'a' && flag ==1)
		    		  {
		    			  sb.append((char)7);
	  	                  getChar();
		    		  }
		    		  if (ch == 'b' && flag ==1)
		    		  {
		    			  sb.append((char)8);
	  	                  getChar();
		    		  }
		    		  if (ch == 'f' && flag ==1)
		    		  {
		    			  sb.append((char)12);
	  	                  getChar();
		    		  }
		    		  if (ch == 'r' && flag ==1)
		    		  {
		    			  sb.append((char)13);
	  	                  getChar();
		    		  }
		    		  if (ch == 't' && flag ==1)
		    		  {
		    			  sb.append((char)9);
	  	                  getChar();
		    		  }
		    		  if (ch == 'v' && flag ==1)
		    		  {
		    			  sb.append((char)11);
	  	                  getChar();
		    		  }
		    		  if (ch == '\\' && flag ==1)
		    		  {
		    			  sb.append((char)92);
	  	                  getChar();
		    		  }
		    		  if (ch == '"' && flag ==1)
		    		  {
		    			  sb.append((char)34);
	  	                  getChar();
		    		  }
		    		  if (ch == '\'' && flag ==1)
		    		  {
		    			  sb.append((char)39);
	  	                  getChar();
		    		  }
		    		  
		    		  if(flag == 0)
		    		  {
		    			  sb.append((char)ch);
	  	                  getChar();
	  	                  if (ch == -1)
	  	                  {
	  	                	throw new LexicalException("String missing single quote!");
	  	                  }
		    		  }
		    	  }
		    	  sb.append('\'');
		    	  t = new Token(STRINGLIT,sb.toString(),pos,line);
		    	  getChar();
		    	  
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
		    	      while (Character.isJavaIdentifierPart(ch)) {
		    	            sb.append((char)ch);
		    	            getChar();
		    	      }
		    	      
		    	      String s = sb.toString();
		    	      
		    	       if(keywords.containsKey(s)) 
		    	       {
		    	    	   t = new Token(keywords.get(s),sb.toString(),pos,line);
		    	       }
		    	       else
		    	       {
		    	    	   t = new Token(NAME,sb.toString(),pos,line);
		    	       }
    	  
		    	  
		      }break;
		      
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

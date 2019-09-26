/**
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

public class Token {
	public enum Kind {
		NAME,
		INTLIT,
		KW_and,
		KW_break,
		KW_do,
		KW_else,
		KW_elseif,
		KW_end,
		KW_false,
		KW_for,
		KW_function,
		KW_goto,
		KW_if,
		KW_in,
		KW_local,
		KW_nil,
		KW_not,
		KW_or,
		KW_repeat,
		KW_return,
		KW_then,
		KW_true,
		KW_until,
		KW_while,
		OP_PLUS, // +
		OP_MINUS, // -
		OP_TIMES, // *
		OP_DIV, // /
		OP_MOD, // %
		OP_POW, // ^
		OP_HASH, // #
		BIT_AMP, // &
		BIT_XOR, // ~
		BIT_OR,  //  |
		BIT_SHIFTL, // <<
		BIT_SHIFTR, //  >>
		OP_DIVDIV, // //
		REL_EQEQ,  // ==
		REL_NOTEQ, // ~=
		REL_LE, // <=
		REL_GE, // >=
		REL_LT, // <
		REL_GT, // > 
		ASSIGN, // =
		LPAREN, 
		RPAREN,
		LCURLY,
		RCURLY,
		LSQUARE,
		RSQUARE,
		COLONCOLON, // ::
		SEMI,
		COLON,
		COMMA,
		DOT,   // .
		DOTDOT,  // .. 
		DOTDOTDOT, // ... 
		STRINGLIT, //
		EOF;
	}
	
	public final Kind kind;
	public final String text;
	public final int pos;
	public final int line;
	

	public Token(Kind kind, String text, int pos, int line) {
		super();
		this.kind = kind;
		this.text = text;
		this.pos = pos;
		this.line = line;
	}

	@Override
	public String toString() {
		
		String t = "";
		if (kind == kind.INTLIT)
		{
			t = Integer.toString(getIntVal());
		}
		/*
		if (kind == kind.STRINGLIT)
		{
			t = getStringVal();
		}
		*/
		if (kind == kind.NAME)
		{
			t = getName();
		}
		else
			t=text;
		
		return "Token [kind=" + kind + ", text=" + t + ", pos=" + (pos+1) + ", line=" + (line+1) + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + line;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (kind != other.kind)
			return false;
		if (line != other.line)
			return false;
		return true;
	}

	/*precondition:  kind is NUMLIT */
	public int getIntVal() {
		return Integer.parseInt(text); 
	}
	
	/*precondition:  kind is STRINGLIT */
	public String getStringVal() {
		return text.substring(1,text.length()-1);  //remove delimiters
	}
	
	/*precondition:   kind is NAME */
	public String getName() {
		return text;
	}
}

/**
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */

package cop5556fa19.AST;

import static cop5556fa19.Token.Kind.KW_false;

import cop5556fa19.Token;

public class ExpFalse extends Exp {

	public ExpFalse(Token firstToken) {
		super(firstToken);
	}
	
	public static final ExpFalse constantFalse = new ExpFalse(new Token(KW_false,"false",0,0));

	@Override
	public String toString() {
		return "ExpFalse []";
	}



	@Override
	public int hashCode() {
		return 0;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}



	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpFalse(this,arg);
	}

}

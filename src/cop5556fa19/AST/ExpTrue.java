package cop5556fa19.AST;

import cop5556fa19.Token;

public class ExpTrue extends Exp {

	
	public static final ExpTrue expTrueConst = new ExpTrue(null);
	
	public ExpTrue(Token firstToken) {
		super(firstToken);
	}
	
	

	@Override
	public String toString() {
		return "ExpTrue [firstToken=" + firstToken + "]";
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
		return v.visitExpTrue(this,arg);
	}



}

package cop5556fa19.AST;

import cop5556fa19.Token;

public class ExpFunction extends Exp {
	
	public final FuncBody body;



	public ExpFunction(Token firstToken, FuncBody body) {
		super(firstToken);
		this.body = body;
	}



	@Override
	public String toString() {
		return "FuncDec [body=" + body + ", firstToken=" + firstToken + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
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
		ExpFunction other = (ExpFunction) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}



	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFunDef(this,arg);
	}

}

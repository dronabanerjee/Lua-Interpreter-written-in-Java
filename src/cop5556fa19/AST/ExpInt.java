package cop5556fa19.AST;

import static cop5556fa19.Token.Kind.*;

import cop5556fa19.Token;

public class ExpInt extends Exp {
	
	public final int v;


	
	public ExpInt(Token first) {
		super(first);
		this.v = first.getIntVal();
	}
	
	@Override
	public String toString() {
		return "ExpInt [v=" + v + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + v;
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
		ExpInt other = (ExpInt) obj;
		if (v != other.v)
			return false;
		return true;
	}
	
	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpInt(this, arg);
	}

	
	
	


}

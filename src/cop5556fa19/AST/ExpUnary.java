package cop5556fa19.AST;

import cop5556fa19.Token;
import cop5556fa19.Token.Kind;

public class ExpUnary extends Exp {

	public final Kind op;
	public final Exp e;

	public ExpUnary(Token firstToken, Kind op, Exp e) {
		super(firstToken);
		this.op = op;
		this.e = e;
	}


	@Override
	public String toString() {
		return "ExpUnary [op=" + op + ", e=" + e + ", firstToken=" + firstToken + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
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
		ExpUnary other = (ExpUnary) obj;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitUnExp(this, arg);
	}

}

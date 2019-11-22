package cop5556fa19.AST;

import static cop5556fa19.Token.Kind;

import cop5556fa19.Token;


public class ExpBinary extends Exp {

	public final Exp e0;
	public final Kind op;
	public final Exp e1;

	public ExpBinary(Token firstToken, Exp e0, Token op, Exp e1) {
		super(firstToken);
		this.e0 = e0;
		this.op = op.kind;
		this.e1 = e1;
	}
	
	public ExpBinary(Token firstToken, Exp e0, Kind op, Exp e1) {
		super(firstToken);
		this.e0 = e0;
		this.op = op;
		this.e1 = e1;
	}
	

	@Override
	public String toString() {
		return "ExpBinary [e0=" + e0 + ", op=" + op + ", e1=" + e1 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e0 == null) ? 0 : e0.hashCode());
		result = prime * result + ((e1 == null) ? 0 : e1.hashCode());
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
		ExpBinary other = (ExpBinary) obj;
		if (e0 == null) {
			if (other.e0 != null)
				return false;
		} else if (!e0.equals(other.e0))
			return false;
		if (e1 == null) {
			if (other.e1 != null)
				return false;
		} else if (!e1.equals(other.e1))
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
		return v.visitExpBin(this, arg);
	}

}

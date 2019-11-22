package cop5556fa19.AST;

import cop5556fa19.Token;

public class FuncBody extends ASTNode {

	public final ParList p;
	public final Block b;

	public FuncBody(Token firstToken, ParList p, Block b) {
		super(firstToken);
		this.p = p;
		this.b = b;
	}

	@Override
	public String toString() {
		return "FuncBody [p=" + p + ", b=" + b  + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
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
		FuncBody other = (FuncBody) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFuncBody(this, arg);
	}

}

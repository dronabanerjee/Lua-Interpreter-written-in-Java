package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatWhile extends Stat {

	public final Exp e;
	public final Block b;

	public StatWhile(Token firstToken, Exp e, Block b) {
		super(firstToken);
		this.e = e;
		this.b = b;
	}

	@Override
	public String toString() {
		return "StatWhile [e=" + e + ", b=" + b + ", firstToken=" + firstToken + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((e == null) ? 0 : e.hashCode());
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
		StatWhile other = (StatWhile) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatWhile(this, arg);
	}

}

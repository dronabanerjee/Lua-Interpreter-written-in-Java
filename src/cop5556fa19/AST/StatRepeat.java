package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatRepeat extends Stat {

	public final Block b;
	public final Exp e;

	public StatRepeat(Token firstToken, Block b, Exp e) {
		super(firstToken);
		this.b = b;
		this.e = e;
	}

	@Override
	public String toString() {
		return "StatRepeat [b=" + b + ", e=" + e + ", firstToken=" + firstToken + "]";
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
		StatRepeat other = (StatRepeat) obj;
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
		return v.visitStatRepeat(this, arg);
	}

}

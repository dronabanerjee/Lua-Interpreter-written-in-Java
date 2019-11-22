package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatDo extends Stat {

	public final Block b;

	public StatDo(Token firstToken, Block b) {
		super(firstToken);
		this.b = b;
	}

	@Override
	public String toString() {
		return "StatDo [b=" + b + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
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
		StatDo other = (StatDo) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatDo(this, arg);
	}

}

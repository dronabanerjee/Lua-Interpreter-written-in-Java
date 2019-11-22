package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class StatForEach extends Stat {

	public final List<ExpName> names;
	public final List<Exp> exps;
	public final Block b;

	public StatForEach(Token firstToken, List<ExpName> names, List<Exp> exps, Block b) {
		super(firstToken);
		this.names = names;
		this.exps = exps;
		this.b = b;
	}

	@Override
	public String toString() {
		return "StatForEach [names=" + names + ", exps=" + exps + ", b=" + b + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((exps == null) ? 0 : exps.hashCode());
		result = prime * result + ((names == null) ? 0 : names.hashCode());
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
		StatForEach other = (StatForEach) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (exps == null) {
			if (other.exps != null)
				return false;
		} else if (!exps.equals(other.exps))
			return false;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatForEach(this, arg);
	}

}

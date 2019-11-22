package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatFor extends Stat {

	public final ExpName name;
	public final Exp ebeg;
	public final Exp eend;
	public final Exp einc;
	public final Block g;

	public StatFor(Token firstToken, ExpName n, Exp ebeg, Exp eend, Exp einc, Block g) {
		super(firstToken);
		this.name = n;
		this.ebeg = ebeg;
		this.eend = eend;
		this.einc = einc;
		this.g = g;
	}

	@Override
	public String toString() {
		return "StatFor [name=" + name + ", ebeg=" + ebeg + ", eend=" + eend + ", einc=" + einc + ", g=" + g
				+ ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ebeg == null) ? 0 : ebeg.hashCode());
		result = prime * result + ((eend == null) ? 0 : eend.hashCode());
		result = prime * result + ((einc == null) ? 0 : einc.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		StatFor other = (StatFor) obj;
		if (ebeg == null) {
			if (other.ebeg != null)
				return false;
		} else if (!ebeg.equals(other.ebeg))
			return false;
		if (eend == null) {
			if (other.eend != null)
				return false;
		} else if (!eend.equals(other.eend))
			return false;
		if (einc == null) {
			if (other.einc != null)
				return false;
		} else if (!einc.equals(other.einc))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatFor(this, arg);
	}

}

package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatGoto extends Stat {

	public final Name name;
	
	public StatLabel label;  //ASTNode of label to jump to

	public StatGoto(Token firstToken, Name name) {
		super(firstToken);
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatGoto [name=" + name + ", label=" + label + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		StatGoto other = (StatGoto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatGoto(this, arg);
	}

}

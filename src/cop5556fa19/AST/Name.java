package cop5556fa19.AST;

import cop5556fa19.Token;

public class Name extends ASTNode {

	public final String name;

	public Name(Token firstToken, String name) {
		super(firstToken);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name [name=" + name +  "]";
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
		Name other = (Name) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitName(this, arg);
	}

}

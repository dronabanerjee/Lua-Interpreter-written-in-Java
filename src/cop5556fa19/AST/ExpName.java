package cop5556fa19.AST;

import cop5556fa19.Token;

import static cop5556fa19.Token.Kind.*;

public class ExpName extends Exp {

	public final String name;

	public ExpName(Token firstToken) {
		super(firstToken);
		name = firstToken.text;
	}
	
	//use for testing only
	public ExpName(String name){
		super(new Token(NAME, name, 0,0));
		this.name = name;
	}

	@Override
	public String toString() {
		return "ExpName [name=" + name +  "]";
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
		ExpName other = (ExpName) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpIdent(this, arg);
	}

}

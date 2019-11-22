package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatFunction extends Stat {

	public final FuncName name;
	public final FuncBody body;
	int numLocals;

	public StatFunction(Token firstToken, FuncName name, FuncBody body) {
		super(firstToken);
		this.name = name;
		this.body = body;
	}



	@Override
	public String toString() {
		return "StatFunction [name=" + name + ", body=" + body + ", numLocals=" + numLocals + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
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
		StatFunction other = (StatFunction) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
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
		return v.visitStatFunction(this, arg);
	}

	public int getNumLocals() {
		return numLocals;
	}

	public void setNumLocals(int numLocals) {
		this.numLocals = numLocals;
	}

	
}

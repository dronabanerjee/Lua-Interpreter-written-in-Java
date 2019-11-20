package cop5556fa19.AST;

import cop5556fa19.Token;

public class ExpString extends Exp {
	
	public final String v;

	public ExpString(Token firstToken) {
		super(firstToken);		
		if (firstToken.kind==Token.Kind.STRINGLIT) {
		v = firstToken.getStringVal();
		}
		else {//KIND = NAME
			v= firstToken.text;
		}
	}
	
	
	@Override
	public String toString() {
		return "ExpString [v=" + v  + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v == null) ? 0 : v.hashCode());
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
		ExpString other = (ExpString) obj;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpString(this, arg);
	}


	
	

}

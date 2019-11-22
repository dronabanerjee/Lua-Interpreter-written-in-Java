package cop5556fa19.AST;

import cop5556fa19.Token;

public class FieldImplicitKey extends Field {

	public final Exp exp;

	public FieldImplicitKey(Token firstToken, Exp exp) {
		super(firstToken);
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "FieldImplicitKey [exp=" + exp  + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp == null) ? 0 : exp.hashCode());
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
		FieldImplicitKey other = (FieldImplicitKey) obj;
		if (exp == null) {
			if (other.exp != null)
				return false;
		} else if (!exp.equals(other.exp))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFieldImplicitKey(this, arg);
	}

}

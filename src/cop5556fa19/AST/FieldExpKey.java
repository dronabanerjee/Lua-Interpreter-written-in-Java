package cop5556fa19.AST;

import cop5556fa19.Token;

public class FieldExpKey extends Field {

	public final Exp key;
	public final Exp value;

	public FieldExpKey(Token firstToken, Exp key, Exp value) {
		super(firstToken);
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "FieldExpKey [key=" + key + ", value=" + value  + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		FieldExpKey other = (FieldExpKey) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFieldExpKey(this, arg);
	}

}

package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class FieldList extends ASTNode {

	List<Field> fields;

	public FieldList(Token firstToken, List<Field> fields) {
		super(firstToken);
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "FieldList [fields=" + fields + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
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
		FieldList other = (FieldList) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFieldList(this, arg);
	}

}

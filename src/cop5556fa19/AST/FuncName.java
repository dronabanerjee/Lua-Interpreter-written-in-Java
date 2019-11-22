package cop5556fa19.AST;

import java.util.ArrayList;
import java.util.List;

import cop5556fa19.Token;

public class FuncName extends ASTNode {

	public final List<ExpName> names;
	public final ExpName afterColon;

	public FuncName(Token firstToken, List<ExpName> names, ExpName nameAfterColon) {
		super(firstToken);
		this.names = names;
		this.afterColon = nameAfterColon;
	}

	public FuncName(Token firstToken, ExpName n) {
		super(firstToken);
		names = new ArrayList<>();
		names.add(n);
		afterColon = null;
	}

	@Override
	public String toString() {
		return "FuncName [names=" + names + ", afterColon=" + afterColon  + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afterColon == null) ? 0 : afterColon.hashCode());
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
		FuncName other = (FuncName) obj;
		if (afterColon == null) {
			if (other.afterColon != null)
				return false;
		} else if (!afterColon.equals(other.afterColon))
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
		return v.visitFuncName(this, arg);
	}

}

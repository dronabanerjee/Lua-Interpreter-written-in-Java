package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class ExpList extends ASTNode {

	public final List<Exp> list;

	public ExpList(Token firstToken, List<Exp> list) {
		super(firstToken);
		this.list = list;
	}

	@Override
	public String toString() {
		return "ExpList [list=" + list + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		ExpList other = (ExpList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpList(this, arg);
	}

}

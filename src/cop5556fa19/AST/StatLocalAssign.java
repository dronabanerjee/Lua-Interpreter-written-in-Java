package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class StatLocalAssign extends Stat {

	public final List<ExpName> nameList;
	public final List<Exp> expList;

	public StatLocalAssign(Token firstToken, List<ExpName> nameList, List<Exp> expList) {
		super(firstToken);
		this.nameList = nameList;
		this.expList = expList;
	}

	@Override
	public String toString() {
		return "StatLocalAssign [nameList=" + nameList + ", expList=" + expList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expList == null) ? 0 : expList.hashCode());
		result = prime * result + ((nameList == null) ? 0 : nameList.hashCode());
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
		StatLocalAssign other = (StatLocalAssign) obj;
		if (expList == null) {
			if (other.expList != null)
				return false;
		} else if (!expList.equals(other.expList))
			return false;
		if (nameList == null) {
			if (other.nameList != null)
				return false;
		} else if (!nameList.equals(other.nameList))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatLocalAssign(this, arg);
	}

}

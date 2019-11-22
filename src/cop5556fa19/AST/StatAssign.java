package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class StatAssign extends Stat {
	
	public final List<Exp>  varList;
	public final List<Exp>  expList;
	
	

	public StatAssign(Token firstToken, List<Exp> varList, List<Exp> expList) {
		super(firstToken);
		this.varList = varList;
		this.expList = expList;
	}

	@Override
	public String toString() {
		return "StatAssign [varList=" + varList + ", expList=" + expList + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expList == null) ? 0 : expList.hashCode());
		result = prime * result + ((varList == null) ? 0 : varList.hashCode());
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
		StatAssign other = (StatAssign) obj;
		if (expList == null) {
			if (other.expList != null)
				return false;
		} else if (!expList.equals(other.expList))
			return false;
		if (varList == null) {
			if (other.varList != null)
				return false;
		} else if (!varList.equals(other.varList))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatAssign(this, arg);
	}

}

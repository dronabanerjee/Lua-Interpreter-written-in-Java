package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatLocalFunc extends Stat {
	
	public final FuncName funcName;
	public final FuncBody funcBody;
	
	

	public StatLocalFunc(Token firstToken, FuncName funcName, FuncBody funcBody) {
		super(firstToken);
		this.funcName = funcName;
		this.funcBody = funcBody;
	}



	@Override
	public String toString() {
		return "StatLocalFunc [funcName=" + funcName + ", funcBody=" + funcBody + ", firstToken=" + firstToken + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcBody == null) ? 0 : funcBody.hashCode());
		result = prime * result + ((funcName == null) ? 0 : funcName.hashCode());
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
		StatLocalFunc other = (StatLocalFunc) obj;
		if (funcBody == null) {
			if (other.funcBody != null)
				return false;
		} else if (!funcBody.equals(other.funcBody))
			return false;
		if (funcName == null) {
			if (other.funcName != null)
				return false;
		} else if (!funcName.equals(other.funcName))
			return false;
		return true;
	}



	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatLocalFunc(this,arg);
	}

}

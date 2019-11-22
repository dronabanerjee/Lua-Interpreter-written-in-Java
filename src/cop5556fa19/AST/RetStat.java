package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class RetStat extends Stat {

	public final 
	List<Exp> el;

	public RetStat(Token firstToken, List<Exp> el) {
		super(firstToken);
		this.el = el;
	}

	@Override
	public String toString() {
		return "RetStat [el=" + el +  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((el == null) ? 0 : el.hashCode());
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
		RetStat other = (RetStat) obj;
		if (el == null) {
			if (other.el != null)
				return false;
		} else if (!el.equals(other.el))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitRetStat(this, arg);
	}

}

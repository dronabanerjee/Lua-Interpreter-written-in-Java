package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class StatIf extends Stat {

	public final List<Exp> es;
	public final List<Block> bs;

	public StatIf(Token firstToken, List<Exp> es, List<Block> bs) {
		super(firstToken);
		this.es = es;
		this.bs = bs;
	}

	@Override
	public String toString() {
		return "StatIf [es=" + es + ", bs=" + bs + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bs == null) ? 0 : bs.hashCode());
		result = prime * result + ((es == null) ? 0 : es.hashCode());
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
		StatIf other = (StatIf) obj;
		if (bs == null) {
			if (other.bs != null)
				return false;
		} else if (!bs.equals(other.bs))
			return false;
		if (es == null) {
			if (other.es != null)
				return false;
		} else if (!es.equals(other.es))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitStatIf(this, arg);
	}

}

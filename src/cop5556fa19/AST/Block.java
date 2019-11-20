package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class Block extends Stat {

	public final List<Stat> stats;

	public Block(Token firstToken, List<Stat> stats) {
		super(firstToken);
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "Block [stats=" + stats +  "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
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
		Block other = (Block) obj;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitBlock(this, arg);
	}

}

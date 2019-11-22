package cop5556fa19.AST;

import cop5556fa19.Token;

public class StatLabel extends Stat {
	
	public final Name label;
	Block enclosingBlock;  //block where label appears
	public int index;             //index of label in statement list
	
//	public StatLabel(Token firstToken, Name label) {
//		super(firstToken);
//		this.label = label;
//		Block parent;
//		int index;  
//	}
	
	

//	@Override
//	public String toString() {
//		return "StatLabel [label=" + label + "]";
//	}

	
	public StatLabel(Token firstToken, Name label, Block enclosingBlock, int index) {
	super(firstToken);
	this.label = label;
	this.enclosingBlock = enclosingBlock;
	this.setIndex(index);
}

	@Override
	public String toString() {
		return "StatLabel [label=" + label +  ", index=" + getIndex() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		StatLabel other = (StatLabel) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitLabel(this,arg);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

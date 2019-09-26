/**
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */

package cop5556fa19.AST;

import java.util.List;

import cop5556fa19.Token;

public class ParList extends ASTNode {

	public final List<Name> nameList;
	public final boolean hasVarArgs;

	public ParList(Token firstToken, List<Name> nameList, boolean hasVarArgs) {
		super(firstToken);
		this.nameList = nameList;
		this.hasVarArgs = hasVarArgs;
	}

	@Override
	public String toString() {
		return "ParList [nameList=" + nameList + ", hasVarArgs=" + hasVarArgs + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasVarArgs ? 1231 : 1237);
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
		ParList other = (ParList) obj;
		if (hasVarArgs != other.hasVarArgs)
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
		return v.visitParList(this, arg);
	}

}

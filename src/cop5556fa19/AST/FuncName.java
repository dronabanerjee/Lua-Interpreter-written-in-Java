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

import java.util.ArrayList;
import java.util.List;

import cop5556fa19.Token;

public class FuncName extends ASTNode {

	public final List<Name> names;
	public final Name afterColon;

	public FuncName(Token firstToken, List<Name> names, Name afterColon) {
		super(firstToken);
		this.names = names;
		this.afterColon = afterColon;
	}

	public FuncName(Token firstToken, Name name) {
		super(firstToken);
		names = new ArrayList<>();
		names.add(name);
		afterColon = null;
	}

	@Override
	public String toString() {
		return "FuncName [names=" + names + ", afterColon=" + afterColon + ", firstToken=" + firstToken + "]";
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

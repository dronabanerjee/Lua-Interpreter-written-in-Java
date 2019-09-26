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
import cop5556fa19.AST.ASTVisitor;
import cop5556fa19.AST.Exp;

public class FunctionCall extends Exp {

	public final Exp func;
	public final List<Exp> args;
	public final Name name;

	public FunctionCall(Token firstToken, Exp func, List<Exp> args, Name name) {
		super(firstToken);
		this.func = func;
		this.args = args;
		this.name = name;
	}

	@Override
	public String toString() {
		return "FunctionCall [func=" + func + ", args=" + args + ", name=" + name + ", firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((func == null) ? 0 : func.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FunctionCall other = (FunctionCall) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
			return false;
		if (func == null) {
			if (other.func != null)
				return false;
		} else if (!func.equals(other.func))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitFunctionCall(this, arg);
	}

}

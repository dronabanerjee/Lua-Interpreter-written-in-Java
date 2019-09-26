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

import cop5556fa19.Token;

public abstract class ASTNode {

	final public Token firstToken;

	public ASTNode(Token firstToken) {
		super();
		this.firstToken = firstToken;
	}

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

	public abstract Object visit(ASTVisitor v, Object arg) throws Exception;

}

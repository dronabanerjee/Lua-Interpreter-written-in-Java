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

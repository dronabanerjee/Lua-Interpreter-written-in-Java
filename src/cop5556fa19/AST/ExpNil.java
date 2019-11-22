package cop5556fa19.AST;

import static cop5556fa19.Token.Kind.*;

import cop5556fa19.Token;

public class ExpNil extends Exp {
	
	public static final ExpNil expNilConst = new ExpNil(new Token(KW_nil,"nil",0,0));

	public ExpNil(Token firstToken) {
		super(firstToken);
	}

	@Override
	public String toString() {
		return "ExpNil [firstToken=" + firstToken + "]";
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpNil(this, arg);
	}

}

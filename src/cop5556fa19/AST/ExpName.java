package cop5556fa19.AST;

import static cop5556fa19.Token.Kind.*;

//import cop5556fa19.SymbolTable;
import cop5556fa19.Token;
//import cop5556fa19.SymbolTable.SymbolTableEntry;

public class ExpName extends Exp {

	public final String name;
	int lexicalDiff = -3;  //illegal value to indicate lack of initialization
	int localVarSlot = -4; //illegal value to indicate lack of initialization
	


	public int getLocalVarSlot() {
		return localVarSlot;
	}

	public void setLocalVarSlot(int localVarSlot) {
		this.localVarSlot = localVarSlot;
	}

	public int getLexicalDiff() {
		return lexicalDiff;
	}

	public void setLexicalDiff(int lexicalDiff) {
		this.lexicalDiff = lexicalDiff;
	}

	public ExpName(Token firstToken) {
		super(firstToken);
		name = firstToken.text;
		lexicalDiff = -3; //uninitialized
	}
	
	public ExpName(Token firstToken, int lexicalDiff) {
		super(firstToken);
		name = firstToken.text;
		this.lexicalDiff = lexicalDiff;
	}
	
	//use for testing only
	public ExpName(String name){
		super(new Token(NAME, name, 0,0));
		this.name = name;
	}



	@Override
	public String toString() {
		return "ExpName [name=" + name + ", lexicalDiff=" + lexicalDiff + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ExpName other = (ExpName) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object visit(ASTVisitor v, Object arg) throws Exception {
		return v.visitExpName(this, arg);
	}

}

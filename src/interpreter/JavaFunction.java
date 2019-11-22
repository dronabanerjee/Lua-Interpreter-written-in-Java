package interpreter;

import java.util.List;

import interpreter.ASTVisitorAdapter.TypeException;

public abstract class JavaFunction extends LuaValue {
	
	public abstract List<LuaValue> call(List<LuaValue> functionargs) throws TypeException;

	@Override
	public LuaValue copy() {
		return this;
	}


}

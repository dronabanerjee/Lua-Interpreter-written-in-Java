package interpreter.built_ins;

import java.util.ArrayList;
import java.util.List;

import interpreter.JavaFunction;
import interpreter.LuaInt;
import interpreter.LuaString;
import interpreter.LuaValue;
import interpreter.ASTVisitorAdapter.TypeException;

public class toNumber extends JavaFunction {

	@Override
	public List<LuaValue> call(List<LuaValue> functionargs) throws TypeException {
		LuaValue arg = functionargs.get(0);
		List<LuaValue> retList = new ArrayList<>();
		if (arg instanceof LuaInt) { 
			retList.add(arg);
		}
		if (arg instanceof LuaString) {
			int num = Integer.parseInt(((LuaString)arg).value);
			retList.add(new LuaInt(num));
		}
		throw new TypeException("Cannot convert " + arg + "to a number");
	}

	@Override
	public LuaValue copy() {
		return this;
	}



}

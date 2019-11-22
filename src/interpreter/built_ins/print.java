package interpreter.built_ins;

import java.util.ArrayList;
import java.util.List;

import interpreter.JavaFunction;
import interpreter.LuaValue;

public class print extends JavaFunction{

	@Override
	public List<LuaValue> call(List<LuaValue> functionargs) {
		for (LuaValue v: functionargs) {
			System.out.print(v);
		}
		return new ArrayList<LuaValue>();
	}

	@Override
	public LuaValue copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}



}

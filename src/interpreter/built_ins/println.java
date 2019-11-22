package interpreter.built_ins;

import java.util.ArrayList;
import java.util.List;

import interpreter.JavaFunction;
import interpreter.LuaValue;

public class println extends JavaFunction{

	@Override
	public List<LuaValue> call(List<LuaValue> functionargs) {
		for (LuaValue v: functionargs) {
			System.out.println(v);
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
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

}

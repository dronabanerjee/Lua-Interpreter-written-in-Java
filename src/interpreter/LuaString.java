package interpreter;

public class LuaString extends LuaValue {
	
	public String value;

	public LuaString(LuaString luaString) {
		this.value = luaString.value;
	}
	
	public LuaString(String string) {
		value = string;
	}
	
	static String toString(LuaValue v) {
		if (v instanceof LuaString) return ((LuaString)v).value;
		if (v instanceof LuaInt) return Integer.toString( ((LuaInt)v).v);
		throw new UnsupportedOperationException();
	}

	@Override
	public LuaValue copy() {
		return new LuaString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		LuaString other = (LuaString) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value;
	}
	
	

}

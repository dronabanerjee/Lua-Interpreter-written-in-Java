package interpreter;

public class LuaBoolean extends LuaValue {

	boolean value;

	public LuaBoolean(boolean value) {
		this.value = value;
	}

	@Override
	public LuaValue copy() {
		return new LuaBoolean(this.value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (value ? 1231 : 1237);
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
		LuaBoolean other = (LuaBoolean) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
}

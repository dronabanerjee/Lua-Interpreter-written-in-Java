package interpreter;

public class LuaInt extends LuaValue {
	int v;
	public LuaInt(int v){
		this.v=v;
	}
	LuaInt(){
		this.v=0;
	}
	public static int toInt(LuaValue val) {
		if (val instanceof LuaInt){
			return ((LuaInt)val).v;
		}
		throw new UnsupportedOperationException("attempting to convert " + val + " to int");
	}
	
	public int intValue() {
		return v;
	}
	
	public String toString() {
		return Integer.toString(v);
	}
	
	public static LuaInt valueOf(int v) {
		return new LuaInt(v);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + v;
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
		LuaInt other = (LuaInt) obj;
		if (v != other.v)
			return false;
		return true;
	}
	@Override
	public LuaValue copy() {
		return new LuaInt(this.v);
	}
	
	
}

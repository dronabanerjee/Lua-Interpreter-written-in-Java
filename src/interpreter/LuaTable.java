package interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LuaTable extends LuaValue{

	@SuppressWarnings("serial")
	public class IllegalTableKeyException extends RuntimeException {

	}



	static final int DEFAULT_ARRAY_SIZE = 32;
	
	LuaValue[] array ;
	Map<LuaValue,LuaValue> map ;
	int arraySize; 
	int nextImplicit;
	

	
	public LuaTable(){
		arraySize = DEFAULT_ARRAY_SIZE;
		array = new LuaValue[arraySize] ;
		Arrays.fill(array, LuaNil.nil);
		map = new HashMap<>();
		nextImplicit = 0;
	}
	
	

	public LuaTable(LuaValue[] array, Map<LuaValue, LuaValue> map, int arraySize) {
		super();
		this.array = array;
		this.map = map;
		this.arraySize = arraySize;
	}



	LuaValue get(LuaValue key)  {
		if (key instanceof LuaInt) {
			int i = ((LuaInt) key).intValue();
			if (0 < i && i <= arraySize) {
				LuaValue val = array[i - 1];
				return val;
			}
		}
		return getFromHash(key);
	}
	
	public void put(String key, LuaValue val)  {
		LuaString ls = new LuaString(key);
		put(ls,val);
	}
	
	LuaValue get(String key) {
		LuaString ls = new LuaString(key);
		return get(ls);
	}
	
	public void putImplicit(LuaValue val) {
		if (0 <= nextImplicit && nextImplicit < arraySize) {
			array[nextImplicit] = val;
			nextImplicit++;
			return;
		}
		LuaInt key = new LuaInt(nextImplicit+1);
		putInHash(key,val);
	}
	
	public void put(LuaValue key, LuaValue val) {
		if (key instanceof LuaNil) throw new IllegalTableKeyException();
		if (key instanceof LuaInt) {
			int i = ((LuaInt) key).intValue();
			if (0 < i && i <= arraySize) {
				array[i-1] = val;
				return;
			}
		}
		putInHash(key,val);
	}

	/** if value is known to be a string, go directly to the hash */
	LuaValue get(LuaString key) {
		return getFromHash(key);
	}

	LuaValue getFromHash(LuaValue key) {
		LuaValue val = map.get(key);
		return val != null ? val : LuaNil.nil;
	}

	void putInHash(LuaValue key, LuaValue val) {
		if (key instanceof LuaNil) throw new IllegalTableKeyException();
		map.put(key, val);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("array:\n");
		for (int i = 0; i < arraySize; i++) {
			LuaValue v = array[i];
			if (! v.equals(LuaNil.nil)) {
				sb.append((i+1)).append(":").append(v).append('\n');
			}
		}
		sb.append("hash:\n");
		map.forEach( (key, value) -> {sb.append(key).append(":").append(value).append('\n');});
		return sb.toString();
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		result = prime * result + arraySize;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		LuaTable other = (LuaTable) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		if (arraySize != other.arraySize)
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}



	@Override
	public LuaValue copy() {
		return new LuaTable(array, map, arraySize);
	}
	
	
	
}

package interpreter;

/**
 * nil is a type, but we only need one instance of the object.  The constructor has
 * been made private to prevent additional instances from being created.
 * 
 * @author Beverly Sanders
 *
 */
public class LuaNil extends LuaValue {
	
	public static final LuaValue nil = new LuaNil();  
	
	public String toString() {
		return "nil";
	}

	@Override
	public LuaValue copy() {
		return nil;
	}
	
	private LuaNil() {}
}

package interpreter;

public abstract class LuaValue {
	/* superclass of all LuaVals */
	LuaValue() {}
	
	/**
	 * Subclasses should override this with an appropriate copy method.  
	 * 
	 * @return
	 */
	public abstract LuaValue copy();
	

}
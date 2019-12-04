package cop5556fa19;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import cop5556fa19.Parser.SyntaxException;
import interpreter.ASTVisitorAdapter;
import interpreter.Interpreter;
import interpreter.LuaInt;
import interpreter.LuaNil;
import interpreter.LuaString;
import interpreter.LuaTable;
import interpreter.LuaValue;
import interpreter.StaticSemanticException;

	class TestInterpreter{

		// To make it easy to print objects and turn this output on and off
		static final boolean doPrint = true;
//		static final boolean doPrint = false;

		private void show(Object input) {
			if (doPrint) {
				System.out.println(input);
			}
		}
				

		/**
		 * scans, parses, and interprets a program representing a Lua chunk.
		 * 
		 * @param input  String containing program source code
		 * @return  a (possbily empty) list of  LuaValue objects.
		 * 
		 * @throws Exception
		 * 
		 *Exceptions may be thrown for various static or dynamic errors
		 */
		
		List<LuaValue> interpret(String input) throws Exception{
			ASTVisitorAdapter lua = new Interpreter();
			Reader r = new StringReader(input);
			List<LuaValue> ret = (List<LuaValue>) lua.load(r);	
			return ret;
		}
		
		/**
		 * Utility function for tests. The interpret function may return a List<LuaValue>
		 * whose contents may be compared with expected using assertions.  This function 
		 * helps construct the "expected" object.
		 * 
		 * @param v  variable length list of ints
		 * @return   List<LuaValue> with value corresponding to input params
		 * 
		 */
		List<LuaValue> makeExpectedWithInts(int ... v){
			List<LuaValue> l = new ArrayList<>();
			for (int i: v) {
				l.add(new LuaInt(i));
			}
			return l;
		}
		
		
		@Test
		void runEmpty() throws Exception{
			String input = "";
			List<LuaValue> ret = interpret(input);
			List<LuaValue> expected = null;
			assertEquals(expected,ret);
		}
		
		@Test
		void run1() throws Exception{
			String input = "return 42";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			LuaValue[] vals = {new LuaInt(42)};
			List<LuaValue> expected = Arrays.asList(vals);
			assertEquals(expected, ret);
		}
		
		@Test
		void run2() throws Exception{
			String input = "x=35 return x";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			LuaValue[] vals = {new LuaInt(35)};
			List<LuaValue> expected = Arrays.asList(vals);
			assertEquals(expected, ret);
		}
			
		@Test
		void fail_run2returns() throws Exception{
			String input = "return 42; return 53;";
			show(input);
			assertThrows(SyntaxException.class,()->{
				List<LuaValue> ret = interpret(input);
				show(ret);
			});		
		}
		
		
		@Test
		void run3() throws Exception{
			String input = "do return 42 end return 53 ";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(42);
			assertEquals(expected, ret);
		}
		

		
		@Test 
		void if0() throws Exception {
			String input = "if true then x=3 end return x";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(3);
			assertEquals(expected, ret);
		}
		
		@Test 
		void if1() throws Exception {
			String input = "if false then x=3 end return x";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaNil.nil);
			assertEquals(expected, ret);
		}
		
		@Test 
		void ifnilIsFalse() throws Exception {
			String input = "if x then x=3 end return x";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaNil.nil);
			assertEquals(expected, ret);
		}
		
		@Test 
		void ifzeroistrue() throws Exception {
			String input = "if 0 then x=3 end return x";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(3);
			assertEquals(expected, ret);
		}
		
		@Test 
		void if2() throws Exception {
			String input = "if x then x=3 elseif y then y=4 elseif true then x=10 else y=11 end return x,y";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaInt.valueOf(10));
			expected.add(LuaNil.nil);
			assertEquals(expected, ret);
		}
		
		@Test 
		void fail_ifgoto() throws Exception {
			String input = "y = 0 if x then x=3 elseif y then y=4 goto label1 elseif true then ::label1:: x=10  else y=11 end z = 12 y=20 return x,y,z";
			show(input);
			assertThrows(StaticSemanticException.class,()->{
				List<LuaValue> ret = interpret(input);
			});		
		}
		
		@Test 
		void if3() throws Exception {
			String input = "if x then x=3 elseif y then y=4 elseif true then x=10 goto label1 else y=11 end z = 12 ::label1:: y=20 return x,y,z";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaInt.valueOf(10));
			expected.add(LuaInt.valueOf(20));
			expected.add(LuaNil.nil);
			assertEquals(expected, ret);
		}
		
		@Test 
		void goto0() throws Exception {
			String input = "if x then x=3 elseif y then y=4 elseif true then do x=10 goto label1 end else y=11 end z = 12 ::label1:: y=20 return x,y,z";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaInt.valueOf(10));
			expected.add(LuaInt.valueOf(20));
			expected.add(LuaNil.nil);
			assertEquals(expected, ret);
		}
		
		@Test 
		void gotoscopedlabels1() throws Exception {
			String input = "x = 0 "
					+ "\nif x "
					+ "\n  then x=3 "
					+ "\n  elseif y "
					+ "\n    then y=4 "
					+ "\n    elseif true "
					+ "\n      then do x=10 goto label1 end "
					+ "\n      else y=11 "
					+ "\nend "
					+ "\nz = 12 "
					+ "\n::label1:: "
					+ "\ny=20 "
					+ "\nreturn x,y,z";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(3,20,12);
			assertEquals(expected, ret);
		}
		
		@Test 
		void fail_gotoscopedlabels() throws Exception { //missing label
			String input = "if x "
					+ "\n  then x=3 "
					+ "\n  elseif y "
					+ "\n    then y=4 "
					+ "\n    elseif true "
					+ "\n      then do x=10 goto label1 end "
					+ "\n      else y=11 "
					+ "\nend "
					+ "\nz = 12 "
					+ "\ny=20 "
					+ "\nreturn x,y,z";
			show(input);
			assertThrows(StaticSemanticException.class,()->{
				List<LuaValue> ret = interpret(input);
			});		
		}
		
		@Test 
		void gotoscopedlabels2() throws Exception { 
			String input = "do x=1 do y=2 do a = 4 goto label1 b=5 ::label1:: z=3 end ::label1:: w=6 end end return w,x,y,z,a,b";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(6,1,2,3,4,5);
			expected.set(5,LuaNil.nil);
			assertEquals(expected, ret);			
		}
		
		@Test 
		void fail_gotoscopedlabels2() throws Exception { 
			String input = "do x=1 do y=2 do a = 4 goto label1 b=5  z=3 end ::label1:: w=6 end end return w,x,y,z,a,b";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(6,1,2,3,4,5);
			expected.set(3,LuaNil.nil);
			expected.set(5,LuaNil.nil);
			assertEquals(expected, ret);			
		}
		
		@Test
		void while0() throws Exception {
			String input = "i = 5  sum = 0 while i>0 do dummy=print(i) dummy=println(\",\") sum = sum + i   i=i-1 end return sum,i";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(15,0);
			assertEquals(expected,ret);
		}
		
		@Test
		void break0() throws Exception {
			String input = "x=1 do x=2 do x=3 do break x=4 y0=0 end y1=1 end y2=2 end return x,y0,y1,y2 ";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);		
			List<LuaValue> expected = new ArrayList<>();
			expected.add(LuaInt.valueOf(3));
			expected.add(LuaNil.nil);
			expected.add(LuaInt.valueOf(1));
			expected.add(LuaInt.valueOf(2));			
			assertEquals(expected, ret);
		}
		
		@Test
		void whilebreak0() throws Exception {
			String input = "i = 10  "
					+ "\nsum = 0 "
					+ "\nwhile i>0 "
					+ "\ndo if i < 4 "
					+ "\n   then break end"
					+ "\ndummy=println(i) "
					+ "\nsum = sum + i   "
					+ "\ni=i-1 "
					+ "\n end "
					+ "\n dummy=println(\"end of loop\")"
					+ "\nreturn sum,i";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(49,3);
			assertEquals(expected,ret);			
		}
		
		
		@Test
		void whilebreak1() throws Exception {
			String input = "i = 10  "
					+ "\nsum = 0 "
					+ "\nwhile i>0 "
					+ "\ndo if i < 4 "
					+ "\n   then do break end end"  //should break out of do, not just enclosing block
					+ "\ndummy=println(i) "
					+ "\nsum = sum + i   "
					+ "\ni=i-1 "
					+ "\n end "
					+ "\n dummy=println(\"end of loop\")"
					+ "\nreturn sum,i";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expected = makeExpectedWithInts(49,3);
			assertEquals(expected,ret);			
		}	
		
		
		@Test
		void table5() throws Exception {
			String input = ""
					+ "f1 = 777"
					+ "\na = { [f1] = g, "
					+ "\n\"x\","
					+ "\n--\"y\", "
					+ "\nx = 1, "
					+ "\nf1, "
					+ "\n[30] = 23, "
					+ "\n45 } "
					+ "\nreturn a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.putImplicit(new LuaString("x"));
			expected.putImplicit(new LuaInt(777));
			expected.putImplicit(new LuaInt(45));
			expected.put(new LuaInt(30), new LuaInt(23));
			expected.put(new LuaString("x"), new LuaInt(1));
			expected.put(new LuaInt(777), LuaNil.nil);			
			assertEquals(expectedList,ret);
		}
		
		@Test
		void table0() throws Exception {
			String input = "a = {} return a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			assertEquals(expectedList,ret);
		}
		
		@Test
		void table1() throws Exception {
			String input = "a = {\"x\", 2, 3} return a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.putImplicit( new LuaString( "x" ));
			expected.putImplicit(new LuaInt(2));
			expected.putImplicit(new LuaInt(3));			
			assertEquals(expectedList,ret);
		}
		
		@Test
		void table2() throws Exception {
			String input = "a = {[\"x\"]= 2, [\"y\"]=3} return a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);			
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.put(new LuaString("x"), new LuaInt(2));
			expected.put(new LuaString("y"), new LuaInt(3)); 		
			assertEquals(expectedList,ret);
		}
		
		@Test
		void table3() throws Exception {
			String input = "a = {x=2, y=3} return a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.put(new LuaString("x"), new LuaInt(2));
			expected.put(new LuaString("y"), new LuaInt(3)); 		
			assertEquals(expectedList,ret);
		}
		
		
		@Test
		void table4() throws Exception {
			String input = "x = \"hello\" y= \"goodbye\" a = {[x]=2, [y]=3} return a";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.put(new LuaString("hello"), new LuaInt(2));
			expected.put(new LuaString("goodbye"), new LuaInt(3)); 	
			assertEquals(expectedList,ret);
		}
		

		
		@Test
		void gotoTest0prep() throws Exception {
			String input = " x=2"
					+ "\n y=3 "
					+ "\n y=4 "
					+ "\n ::label1:: "
					+ "\n return y"
					;
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expected = makeExpectedWithInts(4);
			assertEquals(expected,ret);
		}
		
		@Test
		void gotoTest0() throws Exception {
			String input = " x=2"
					+ "\n y=3 goto label1 "
					+ "\n y=4 "
					+ "\n ::label1:: "
					+ "\n return y"
					;
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expected = makeExpectedWithInts(3);
			assertEquals(expected,ret);
		}
		
		@Test
		void gotoTest1() throws Exception{
			String input = "a=1; b=2; do a=3 goto label1 end b=3 ::label1:: a=4 return a,b";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);		
			List<LuaValue> expected = makeExpectedWithInts(4,2);
			assertEquals(expected,ret);
		}
		
		@Test
		void gotoTest2() throws Exception{
			String input = "a=0; do a=1 do a=2 goto label1  a=3 end a=4 end a=5 ::label1:: b=6 return a,b";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expected = makeExpectedWithInts(2,6);
			assertEquals(expected,ret);			
		}
		
		@Test
		void testBinary0() throws Exception{
			String input = "a=2+3 b=3-a c=2*4 d = c/2 e = c%3 return a,b,c,d,e";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expected = makeExpectedWithInts(5,-2,8,4,2);
			assertEquals(expected,ret);						
		}
		
		
		@Test
		void testSetField0() throws Exception{
			String input = "a = 4; t={} ; t[a]=5; return t";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.put(new LuaInt(4), new LuaInt(5));
			assertEquals(expectedList,ret);
		}
		

		
		@Test 
		void testSetField1() throws Exception{
			String input = "a = {1,2,3} t= {a} dummy = print(t[1][3]) return t";
			show(input);
			List<LuaValue> ret = interpret(input);
			show(ret);	
			List<LuaValue> expectedList = new ArrayList<>();
			LuaTable a = new LuaTable();
			a.put(new LuaInt(1),new LuaInt(1));
			a.put(new LuaInt(2),new LuaInt(2));
			a.put(new LuaInt(3),new LuaInt(3));
			
			LuaTable expected = new LuaTable();
			expectedList.add(expected);
			expected.put(new LuaInt(1), a);
			assertEquals(expectedList,ret);
		}
		

}

/* *
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites or repositories,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */

package cop5556fa19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cop5556fa19.Scanner.LexicalException;

import static cop5556fa19.Token.Kind.*;

class ScannerTest {
	
	//I like this to make it easy to print objects and turn this output on and off
	static boolean doPrint = true;
	private void show(Object input) {
		if (doPrint) {
			System.out.println(input.toString());
		}
	}
	
	

	 /**
	  * Example showing how to get input from a Java string literal.
	  * 
	  * In this case, the string is empty.  The only Token that should be returned is an EOF Token.  
	  * 
	  * This test case passes with the provided skeleton, and should also pass in your final implementation.
	  * Note that calling getNext again after having reached the end of the input should just return another EOF Token.
	  * 
	  */
	@Test 
	void test0() throws Exception {
		Reader r = new StringReader("");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext()); 
		assertEquals(EOF, t.kind);
		show(t= s.getNext());
		assertEquals(EOF, t.kind);
	}

	/**
	 * Example showing how to create a test case to ensure that an exception is thrown when illegal input is given.
	 * 
	 * This "@" character is illegal in the final scanner (except as part of a String literal or comment). So this
	 * test should remain valid in your complete Scanner.
	 */
	@Test
	void test1() throws Exception {
		Reader r = new StringReader("@");
		Scanner s = new Scanner(r);
        assertThrows(LexicalException.class, ()->{
		   s.getNext();
        });
	}
	
	/**
	 * Example showing how to read the input from a file.  Otherwise it is the same as test1.
	 *
	 */
	@Test
	void test2() throws Exception {
		String file = "C:\\Users\\user\\eclipse-workspace\\Proj2\\src\\testInputFiles\\test2.input";
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		//System.out.println((char)r.read());
        assertThrows(LexicalException.class, ()->{
		   s.getNext();
        });
	}
	

	
	/**
	 * Another example.  This test case will fail with the provided code, but should pass in your completed Scanner.
	 * @throws Exception
	 */
	@Test
	void test3() throws Exception {
		Reader r = new StringReader(",,:==");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,COMMA);
		assertEquals(t.text,",");
		show(t = s.getNext());
		assertEquals(t.kind,COMMA);
		assertEquals(t.text,",");
		
		show(t = s.getNext());
		assertEquals(t.kind,COLON);
		assertEquals(t.text,":");
		
		show(t = s.getNext());
		assertEquals(t.kind,REL_EQEQ);
		assertEquals(t.text,"==");
	}
	
	@Test
	void test4() throws Exception {
		Reader r = new StringReader("(");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(LPAREN, t.kind);
		assertEquals("(",t.text);
	}
	
	@Test
	void test5() throws Exception {
		Reader r = new StringReader("97*2");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(INTLIT, t.kind);
		assertEquals("97",t.text);
		show(t= s.getNext());
		assertEquals(OP_TIMES, t.kind);
		assertEquals("*",t.text);
		show(t= s.getNext());
		assertEquals(INTLIT, t.kind);
		assertEquals("2",t.text);
		
	}
	
	@Test
	void test6() throws Exception {
		Reader r = new StringReader("if(a==b and c==d)");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(KW_if, t.kind);
		assertEquals("if",t.text);
		show(t= s.getNext());
		assertEquals(LPAREN, t.kind);
		assertEquals("(",t.text);
		show(t= s.getNext());
		assertEquals(NAME, t.kind);
		assertEquals("a",t.text);
		show(t= s.getNext());
		assertEquals(REL_EQEQ, t.kind);
		assertEquals("==",t.text);
		show(t= s.getNext());
		assertEquals(NAME, t.kind);
		assertEquals("b",t.text);
		show(t= s.getNext());
		assertEquals(KW_and, t.kind);
		assertEquals("and",t.text);
		show(t= s.getNext());
		assertEquals(NAME, t.kind);
		assertEquals("c",t.text);
		show(t= s.getNext());
		assertEquals(REL_EQEQ, t.kind);
		assertEquals("==",t.text);
		show(t= s.getNext());
		assertEquals(NAME, t.kind);
		assertEquals("d",t.text);
		show(t= s.getNext());
		assertEquals(RPAREN, t.kind);
		assertEquals(")",t.text);
		
	}
	
	@Test
	void test7() throws Exception {
		Reader r = new StringReader("\"bla\"");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(STRINGLIT, t.kind);
		assertEquals("\"bla\"",t.text);
		
	}
	
	@Test
	void test8() throws Exception {
		Reader r = new StringReader("( --bla bla,,,,+");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(LPAREN, t.kind);
		assertEquals("(",t.text);
		
	}

}

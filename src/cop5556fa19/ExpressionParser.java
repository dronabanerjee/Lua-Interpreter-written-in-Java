/**
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */

package cop5556fa19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cop5556fa19.AST.Block;
import cop5556fa19.AST.Exp;
import cop5556fa19.AST.ExpBinary;
import cop5556fa19.AST.ExpFalse;
import cop5556fa19.AST.ExpFunction;
import cop5556fa19.AST.ExpInt;
import cop5556fa19.AST.ExpName;
import cop5556fa19.AST.ExpNil;
import cop5556fa19.AST.ExpString;
import cop5556fa19.AST.ExpTable;
import cop5556fa19.AST.ExpTrue;
import cop5556fa19.AST.ExpUnary;
import cop5556fa19.AST.ExpVarArgs;
import cop5556fa19.AST.Field;
import cop5556fa19.AST.FieldExpKey;
import cop5556fa19.AST.FieldImplicitKey;
import cop5556fa19.AST.FieldNameKey;
import cop5556fa19.AST.FuncBody;
import cop5556fa19.AST.Name;
import cop5556fa19.AST.ParList;
import cop5556fa19.Token.Kind;
import static cop5556fa19.Token.Kind.*;

public class ExpressionParser {
	
	@SuppressWarnings("serial")
	class SyntaxException extends Exception {
		Token t;
		
		public SyntaxException(Token t, String message) {
			super(t.line + ":" + t.pos + " " + message);
		}
	}
	
	final Scanner scanner;
	Token t;  //invariant:  this is the next token


	ExpressionParser(Scanner s) throws Exception {
		this.scanner = s;
		t = scanner.getNext(); //establish invariant
	}


	Exp exp() throws Exception {
		Token first = t;
		Token op;
		Exp e0 = null;
		Exp e1 = null;
		if(isKind(OP_MINUS) || isKind(KW_not) || isKind(OP_HASH) || isKind(BIT_XOR))
		{
		    op = consume();
			e1 = exp();
			e0 = new ExpUnary(first, op.kind, e1);
		}
		else
		{
			e0 = andExp();
			while (isKind(OP_PLUS) || isKind(OP_MINUS) || isKind(OP_TIMES) || isKind(OP_DIV) || isKind(OP_DIVDIV) || isKind(OP_POW) || isKind(OP_MOD) || isKind(BIT_AMP) || isKind(BIT_XOR) || isKind(BIT_OR) || isKind(BIT_SHIFTL) || isKind(BIT_SHIFTR) || isKind(DOTDOT) || isKind(REL_LE) || isKind(REL_GE) || isKind(REL_LT) || isKind(REL_GT) || isKind(REL_EQEQ) || isKind(REL_NOTEQ) || isKind(KW_and) || isKind(KW_or)) {
				op = consume();
				e1 = andExp();
				e0 = new ExpBinary(first, e0, op, e1);
			}
		}
		return e0;
	}

	
private Exp andExp() throws Exception{
		// TODO Auto-generated method stub
		Exp e = null;
		switch(t.kind)
		{
			case INTLIT:
				e = new ExpInt(t);
				consume();
				break;
			
			case STRINGLIT:
				e = new ExpString(t);
				consume();
				break;
				
			case KW_false:
				e = new ExpFalse(t);
				consume();
				break;
				
			case KW_true:
				e = new ExpTrue(t);
				consume();
				break;
				
			case KW_nil:
				e = new ExpNil(t);
				consume();
				break;
				
			case DOTDOTDOT:
				e = new ExpVarArgs(t);
				consume();
				break;
				
			case KW_function:
				FuncBody fb = null;
				Token ft = consume();
				Token ftf = ft;
				Token ftp = null;
				ParList p = null;
				Block b = null;
				List<Name> nameList = new ArrayList<>();
				Name n = null;
				if(isKind(LPAREN))
				{
					ft = consume();
					if(isKind(DOTDOTDOT) || isKind(NAME) || isKind(RPAREN))
					{
						if(isKind(DOTDOTDOT))
						{
							p = new ParList(t, nameList, true);
							consume();
							if(!isKind(RPAREN))
							{
								throw new SyntaxException(t, "Missing ) in function body!");
							}
							else
							{
								consume();
								if(isKind(KW_end))
								{
									fb = new FuncBody(ft, p, null);
								}
								else
								{
									throw new SyntaxException(t, "Function body is missing Keyword end or block is not null!");
								}
							}
						}
						else if(isKind(RPAREN))
						{
							consume();
							if(isKind(KW_end))
							{
								fb = new FuncBody(ft, p, null);
							}
							else
							{
								throw new SyntaxException(t, "Function body is missing Keyword end or block is not null!");
							}
						}
						else
						{
							int flag =0;
							while(!isKind(RPAREN))
							{
								if(isKind(NAME) && flag ==0)
								{
									n = new Name(t, t.text);
									nameList.add(n);
									ftp = consume();
									if(isKind(RPAREN))
									{
										consume();
										if(isKind(KW_end))
										{
											p = new ParList(ftp, nameList, false);
											fb = new FuncBody(ft, p, null);
										}
										else
										{
											throw new SyntaxException(t, "Function body is missing Keyword end or block is not null!");
										}
										break;
									}
								}
								
								if(isKind(COMMA))
								{
										flag =1;
										consume();
										if(isKind(DOTDOTDOT))
										{
											p = new ParList(t, nameList, true);
											consume();
											if(!isKind(RPAREN))
											{
												throw new SyntaxException(t, "Invalid ParList!");
											}
											consume();
											if(isKind(KW_end))
											{
												fb = new FuncBody(ft, p, null);
											}
											else
											{
												throw new SyntaxException(t, "Function body is missing Keyword end or block is not null!");
											}
											break;
										}
										if(isKind(NAME))
										{
											n = new Name(t, t.text);
											nameList.add(n);
											consume();
											if(isKind(RPAREN))
											{
												consume();
												if(isKind(KW_end))
												{
													p = new ParList(ftp, nameList, false);
													fb = new FuncBody(ft, p, null);
												}
												else
												{
													throw new SyntaxException(t, "Function body is missing Keyword end or block is not null!");
												}
												break;
											}
											
										}
								}
								else
								{
									throw new SyntaxException(t, "Unexpected token in ParList or Comma is missing");
								}
							}
							
						}
					}
					else
					{
						throw new SyntaxException(t, "Invalid function body!");
					}
				}
				else
				{
					throw new SyntaxException(t, "Expected ( but encountered a different token");
				}
				e = new ExpFunction(ftf, fb);
				consume();
				break;
				
			case NAME:
				e = new ExpName(t);
				consume();
				break;
				
			case LPAREN:
				consume();
				e = exp();
				if(!isKind(RPAREN))
				{
					throw new SyntaxException(t, "Missing )!");
				}
				if(e==null)
				{
					throw new SyntaxException(t, "Invalid Sysntax. Expression between paranthesis is null");
				}
				consume();
				break;
				
			case LCURLY:
				ft = consume();
				Exp fe = null;
				Exp fe_key = null;
				Exp fe_value = null;
				FieldImplicitKey fik = null;
				FieldNameKey fnk = null;
				FieldExpKey fek = null;
				List<Field> fields = new ArrayList<>();
				Name nf = null;
				Token temp_token = null;
				while(!isKind(RCURLY))
				{
					//throw new SyntaxException(t, "Missing }!");
					if(isKind(LSQUARE))
					{
						temp_token = t;
						consume();
						fe_key = exp();
						consume();
						if(isKind(RSQUARE))
						{
							consume();
							if(isKind(ASSIGN))
							{
								consume();
								fe_value = exp();
								fek = new FieldExpKey(temp_token, fe_key, fe_value);
								fields.add(fek);
								consume();
								if(isKind(COMMA) || isKind(SEMI) || isKind(RCURLY))
								{
									if(isKind(COMMA) || isKind(SEMI))
									{
										consume();
									}
								}
								else
								{
									throw new SyntaxException(t, "Field seperator missing!");
								}
							}
							else
							{
								throw new SyntaxException(t, "Invalid FieldExpKey, = missing!");
							}
							
						}
						else
						{
							throw new SyntaxException(t, "Invalid FieldExpKey, ] missing!");
						}
						

					}
					else if(isKind(NAME))
					{
						nf = new Name(t, t.text);
						temp_token = t;
						consume();
						if(isKind(ASSIGN))
						{
							consume();
							fe = exp();
							fnk = new FieldNameKey(temp_token, nf, fe);
							fields.add(fnk);
							consume();
							if(isKind(COMMA) || isKind(SEMI) || isKind(RCURLY))
							{
								if(isKind(COMMA) || isKind(SEMI))
								{
									consume();
								}
							}
							else
							{
								throw new SyntaxException(t, "Field seperator missing!");
							}
						}
						else
						{
							throw new SyntaxException(t, "Invalid FieldNameKey, = missing!");
						}
						
					}
					else
					{
						fe = exp();
						fik = new FieldImplicitKey(t, fe);
						fields.add(fik);
						consume();
						if(isKind(COMMA) || isKind(SEMI) || isKind(RCURLY))
						{
							if(isKind(COMMA) || isKind(SEMI))
							{
								consume();
							}
						}
						else
						{
							throw new SyntaxException(t, "Field seperator missing!");
						}
					}
				}
				e = new ExpTable(ft, fields);
				consume();
				break;
			
			default:
				//throw new UnsupportedOperationException("andExp");  //I find this is a more useful placeholder than returning null.
				throw new SyntaxException(t, "Syntax error!");
		}
		return e;
		
	}


	private Block block() {
		return new Block(null);  //this is OK for Assignment 2
	}


	protected boolean isKind(Kind kind) {
		return t.kind == kind;
	}

	protected boolean isKind(Kind... kinds) {
		for (Kind k : kinds) {
			if (k == t.kind)
				return true;
		}
		return false;
	}

	/**
	 * @param kind
	 * @return
	 * @throws Exception
	 */
	Token match(Kind kind) throws Exception {
		Token tmp = t;
		if (isKind(kind)) {
			consume();
			return tmp;
		}
		error(kind);
		return null; // unreachable
	}

	/**
	 * @param kind
	 * @return
	 * @throws Exception
	 */
	Token match(Kind... kinds) throws Exception {
		Token tmp = t;
		if (isKind(kinds)) {
			consume();
			return tmp;
		}
		StringBuilder sb = new StringBuilder();
		for (Kind kind1 : kinds) {
			sb.append(kind1).append(kind1).append(" ");
		}
		error(kinds);
		return null; // unreachable
	}

	Token consume() throws Exception {
		Token tmp = t;
        t = scanner.getNext();
		return tmp;
	}
	
	void error(Kind... expectedKinds) throws SyntaxException {
		String kinds = Arrays.toString(expectedKinds);
		String message;
		if (expectedKinds.length == 1) {
			message = "Expected " + kinds + " at " + t.line + ":" + t.pos;
		} else {
			message = "Expected one of" + kinds + " at " + t.line + ":" + t.pos;
		}
		throw new SyntaxException(t, message);
	}

	void error(Token t, String m) throws SyntaxException {
		String message = m + " at " + t.line + ":" + t.pos;
		throw new SyntaxException(t, message);
	}
	


}

package cop5556fa19.AST;

import static cop5556fa19.Token.Kind.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cop5556fa19.Token;
import cop5556fa19.Token.Kind;

public class Expressions {
	
	static public ExpBinary makeBinary(int v0, Kind op, int v1) {
		Exp e0 = makeInt(v0);
		Token first = e0.firstToken;
		Token eop = new Token(op, op.toString(),0,0);
		Exp e1 = makeInt(v1);
		return new ExpBinary(first,e0,eop,e1);
	}

	static public ExpInt makeInt(int v0) {
		Token t = new Token(Kind.INTLIT, Integer.toString(v0),0,0);
		return new ExpInt(t);
	}

	static public ExpString makeExpString(String s) {
		Token t = new Token(Kind.STRINGLIT, "\""+s+"\"", 0,0);
		return new ExpString(t);
	}

	public static Exp makeBinary(String s0, Kind op, String s1) {
		Exp es0 = makeExpString(s0);
		Exp es1 = makeExpString(s1);
		Token eop = new Token(op,op.toString(),0,0);
		return new ExpBinary(es0.firstToken,es0,eop,es1);
	}
	
	public static Exp makeBinary(Exp e0, Kind op, Exp e1) {
		Token eop = new Token(op,op.toString(),0,0);
		return new ExpBinary(e0.firstToken, e0, eop, e1);
	}
	
	public static ExpUnary makeExpUnary(Kind op, int i) {
		Token first = new Token(op,op.toString(), 0,0);
		Exp e = makeInt(i);
		return new ExpUnary(first,op,e);
	}

	
	public static ExpUnary makeExpUnary(Kind op, Exp e) {
		Token first = new Token(op,op.toString(), 0,0);
		return new ExpUnary(first,op,e);
	}

	public static ParList makeParList(boolean hasVarArgs, String ...names) {
		List<Name> nameList = new ArrayList<>();
		Token first = new Token(Kind.LPAREN, "(",0,0);
		for (String name: names) {
			Token nfirst = new Token(Kind.NAME,name,0,0);
			Name n = new Name(nfirst, name);
			nameList.add(n);
		}
		return new ParList(first,nameList,hasVarArgs);
	}
	
	public static List<Exp>  makeExpList(Exp ... es){
		List<Exp> elist = new ArrayList<>();
		for (Exp e: es) { elist.add(e); }
		return elist;
	}
	
	public static ExpName makeExpNameGlobal(String name) {
		Token first = new Token(NAME,name,0,0);
		return new ExpName(first,-1);
	}
	
	public static List<Stat>  makeStatList(Stat ... ss){
		List<Stat> slist = new ArrayList<>();
		for (Stat s: ss) { slist.add(s); }
		return slist;
	}

	public static FuncBody makeFuncBody(ParList parList, Block b) {
		return new FuncBody(parList.firstToken, parList,b);
	}

	public static ExpFunction makeExpFunction(FuncBody funcBody) {
		return new ExpFunction(funcBody.firstToken, funcBody);
	}

	public static Block makeEmptyBlock() {
		Token first = new Token(SEMI, "dummy",0,0);
		List<Stat> stats = new ArrayList<>();
		return new Block(first,stats);
	}
	
	public static ExpFunction makeExpFunction(boolean hasVarList, String ...name) {
		return makeExpFunction(Expressions.makeFuncBody(Expressions.makeParList(hasVarList, name), Expressions.makeEmptyBlock()));
	}

	public static Block makeBlock(Stat ... stats) {
		Token first;
		if(stats.length>0) first = stats[0].firstToken;
		else first = new Token(SEMI, "dummy",0,0);
		return new Block(first,new ArrayList<Stat>(Arrays.asList(stats)));
		}
	
	public static StatAssign makeStatAssign(List<Exp> lhs, List<Exp> rhs) {
		Token first = lhs.size()>0 ? lhs.get(0).firstToken : new Token(SEMI, "dummy",0,0);
		return new StatAssign(first,lhs,rhs);
	}
	
	public static StatAssign makeStatAssign(Exp lhs, Exp rhs) {
		List<Exp> lhsList = makeExpList(lhs);
		List<Exp> rhsList = makeExpList(rhs);
		return makeStatAssign(lhsList,rhsList);
	}

	public static ExpInt makeExpInt(int i) {
		Token first = new Token(INTLIT, Integer.toString(i),0,0);
		return new ExpInt(first);
	}

//	public static FunctionCall makeFunCall(Exp n, List<Exp> args, String method) {
//		Token first = n.firstToken;		
//		ExpName m = (method == null) ? null : makeExpName(method);
//		FunctionCall f = new FunctionCall(first,n,args, m);
//		return f;
//	}

	public static ExpFunctionCall makeExpFunCall(Exp n, List<Exp> args, Object object) {
		Token first = n.firstToken;		
		ExpFunctionCall f = new ExpFunctionCall(first,n,args);
		return f;
	}

	public static Exp makeExpTableLookup(Exp table, Exp key) {
		Token first = table.firstToken;
		return new ExpTableLookup(first, table, key);
	}
	
	public static Name makeName(String name) {
			Token first = new Token(NAME,name,0,0);
			return new Name(first, name);
		}
	

	public static StatLabel makeStatLabel(String name) {
		Name n = makeName(name);
		return new StatLabel(n.firstToken,n, null, 0);	
	}

	public static StatGoto makeStatGoto(String name) {
		Token first = new Token(KW_goto,"goto",0,0);
		Name n =  new Name(first, name);
		return new StatGoto(first,n);
	}

	public static StatDo makeStatDo(Stat ... stats) {
		Block b = makeBlock(stats);	
		return new StatDo(b.firstToken, b);
	}

	public static StatBreak makeStatBreak() {
		Token first = new Token(KW_break,"break",0,0);
		return new StatBreak(first);
	}



}

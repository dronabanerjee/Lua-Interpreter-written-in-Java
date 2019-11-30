package interpreter;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cop5556fa19.Token;
import cop5556fa19.AST.ASTVisitor;
import cop5556fa19.AST.Block;
import cop5556fa19.AST.Chunk;
import cop5556fa19.AST.Exp;
import cop5556fa19.AST.ExpBinary;
import cop5556fa19.AST.ExpFalse;
import cop5556fa19.AST.ExpFunction;
import cop5556fa19.AST.ExpFunctionCall;
import cop5556fa19.AST.ExpInt;
import cop5556fa19.AST.ExpList;
import cop5556fa19.AST.ExpName;
import cop5556fa19.AST.ExpNil;
import cop5556fa19.AST.ExpString;
import cop5556fa19.AST.ExpTable;
import cop5556fa19.AST.ExpTableLookup;
import cop5556fa19.AST.ExpTrue;
import cop5556fa19.AST.ExpUnary;
import cop5556fa19.AST.ExpVarArgs;
import cop5556fa19.AST.FieldExpKey;
import cop5556fa19.AST.FieldImplicitKey;
import cop5556fa19.AST.FieldList;
import cop5556fa19.AST.FieldNameKey;
import cop5556fa19.AST.FuncBody;
import cop5556fa19.AST.FuncName;
import cop5556fa19.AST.Name;
import cop5556fa19.AST.ParList;
import cop5556fa19.AST.RetStat;
import cop5556fa19.AST.Stat;
import cop5556fa19.AST.StatAssign;
import cop5556fa19.AST.StatBreak;
import cop5556fa19.AST.StatDo;
import cop5556fa19.AST.StatFor;
import cop5556fa19.AST.StatForEach;
import cop5556fa19.AST.StatFunction;
import cop5556fa19.AST.StatGoto;
import cop5556fa19.AST.StatIf;
import cop5556fa19.AST.StatLabel;
import cop5556fa19.AST.StatLocalAssign;
import cop5556fa19.AST.StatLocalFunc;
import cop5556fa19.AST.StatRepeat;
import cop5556fa19.AST.StatWhile;

public abstract class ASTVisitorAdapter implements ASTVisitor {
	
	public static int returnFlag = 0;
	
	@SuppressWarnings("serial")
	public static class StaticSemanticException extends Exception{
		
			public StaticSemanticException(Token first, String msg) {
				super(first.line + ":" + first.pos + " " + msg);
			}
		}
	
	
	@SuppressWarnings("serial")
	public
	static class TypeException extends Exception{

		public TypeException(String msg) {
			super(msg);
		}
		
		public TypeException(Token first, String msg) {
			super(first.line + ":" + first.pos + " " + msg);
		}
		
	}
	
	public abstract List<LuaValue> load(Reader r) throws Exception;

	@Override
	public Object visitExpNil(ExpNil expNil, Object arg) {
		return LuaNil.nil;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpBin(ExpBinary expBin, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitUnExp(ExpUnary unExp, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpInt(ExpInt expInt, Object arg) throws Exception {
		LuaInt val = new LuaInt(expInt.v);
		return val;
	}

	@Override
	public Object visitExpString(ExpString expString, Object arg) {
		LuaString val = new LuaString(expString.v);
		return val;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpTable(ExpTable expTableConstr, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpList(ExpList expList, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitParList(ParList parList, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitFunDef(ExpFunction funcDec, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitName(Name name, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitBlock(Block block, Object arg) throws Exception {
		System.out.println("In visit block");
		List<LuaValue> blockValue = new ArrayList<>();
		List<Stat> s = block.stats;
		if(s.size() == 0)
		{
			return null;
		}
		for(int i=0; i<s.size(); i++)
		{

			if(s.get(i).getClass() == RetStat.class && returnFlag==0)
			{
				returnFlag=1;
				blockValue.addAll((List<LuaValue>) s.get(i).visit(this, arg));
				break;
			}
			if(returnFlag==0 && s.get(i).getClass() != StatAssign.class)
				blockValue.addAll((List<LuaValue>) s.get(i).visit(this, arg));
				
			if(s.get(i).getClass() == StatAssign.class)
				s.get(i).visit(this, arg);
		}
	    return blockValue;
	}

	@Override
	public Object visitStatBreak(StatBreak statBreak, Object arg, Object arg2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatBreak(StatBreak statBreak, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatGoto(StatGoto statGoto, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatDo(StatDo statDo, Object arg) throws Exception {
		Block doBlock = statDo.b;
		List<LuaValue> bval = (List<LuaValue>) doBlock.visit(this, arg);
		return bval;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatWhile(StatWhile statWhile, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatRepeat(StatRepeat statRepeat, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatIf(StatIf statIf, Object arg) throws Exception {
		List<Exp> eList = statIf.es;
		List<Block> bList = statIf.bs;
		List<LuaValue> bval = new ArrayList<>();
		Exp e;
		Block b;
		LuaValue eval = null;
		LuaBoolean lb = null;
		int i;
		System.out.println("In visit stat if");
		for(i=0; i<eList.size(); i++)
		{
			e = eList.get(i);
			b = bList.get(i);
			eval = (LuaValue) e.visit(this, arg);
			//System.out.println(eList.size());
			//System.out.println(i);
			if(!(eval instanceof LuaNil))
			{
				//System.out.println(eval);
				if(eval instanceof LuaBoolean)
				{
					lb = (LuaBoolean)eval;
					if(lb.value)
					{
						bval = (List<LuaValue>) b.visit(this, arg);
						//System.out.println(bval);
						//System.out.println(eval);
						return bval;
					}
				}
				else
				{
					bval = (List<LuaValue>) b.visit(this, arg);
					return bval;
				}
			}
			
		}
		if(bList.size()>eList.size())
		{
			b = bList.get(i);
			bval = (List<LuaValue>) b.visit(this, arg);
			return bval;
		}
		return bval;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatFor(StatFor statFor1, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatForEach(StatForEach statForEach, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitFuncName(FuncName funcName, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatFunction(StatFunction statFunction, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatLocalFunc(StatLocalFunc statLocalFunc, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatLocalAssign(StatLocalAssign statLocalAssign, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitRetStat(RetStat retStat, Object arg) throws Exception {
		System.out.println("In visit ret stat");
		List<LuaValue> retValue = new ArrayList<>();
		List<Exp> ret = retStat.el;
		if(ret.size() == 0)
		{
			return null;
		}
		for(int i=0; i<ret.size(); i++)
		{
			retValue.add((LuaValue) ret.get(i).visit(this, arg));
		}
	    return retValue;
		
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitChunk(Chunk chunk, Object arg) throws Exception {
		System.out.println("In visit chunk");
		List<LuaValue> chunkValue = new ArrayList<>();
		Block b = chunk.block;
		chunkValue = (List<LuaValue>) b.visit(this, arg);
		if(chunkValue == null)
		{		
			return null;
		}
		return chunkValue;
	}

	@Override
	public Object visitFieldExpKey(FieldExpKey fieldExpKey, Object object) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitFieldNameKey(FieldNameKey fieldNameKey, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Object visitFieldImplicitKey(FieldImplicitKey fieldImplicitKey, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpTrue(ExpTrue expTrue, Object arg) {
		return new LuaBoolean(true);
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpFalse(ExpFalse expFalse, Object arg) {
		return new LuaBoolean(false);
	}

	@Override
	public Object visitFuncBody(FuncBody funcBody, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpVarArgs(ExpVarArgs expVarArgs, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatAssign(StatAssign statAssign, Object arg) throws Exception {
		
		System.out.println("In visit stat assign");
		List<Exp> vList = statAssign.varList;
		List<Exp> eList = statAssign.expList;
		Exp v,e;
		LuaString key = null;
		LuaValue eval = null;;
		for(int i=0; i<vList.size(); i++)
		{
			v = vList.get(i);
			e = eList.get(i);
			
			eval = (LuaValue) e.visit(this, arg);
			if(v instanceof ExpName)
			{
				ExpName v1 = (ExpName)v;
			    key = new LuaString(v1.name);
			    ((LuaTable)arg).put(key, eval);
			}
		}
	    return null;
	}

	@Override
	public Object visitExpTableLookup(ExpTableLookup expTableLookup, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpFunctionCall(ExpFunctionCall expFunctionCall, Object arg) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitLabel(StatLabel statLabel, Object ar) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitFieldList(FieldList fieldList, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object visitExpName(ExpName expName, Object arg) throws Exception {
		
		String ls = expName.name;
		LuaString key = new LuaString(ls);
		LuaValue lv = ((LuaTable)arg).get(key);
		return lv;
	}



}

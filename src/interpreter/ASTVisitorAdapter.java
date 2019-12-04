package interpreter;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cop5556fa19.Token;
import cop5556fa19.Token.Kind;
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
import cop5556fa19.AST.Field;
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
import static cop5556fa19.Token.Kind.*;
import cop5556fa19.AST.StatLabel;
import cop5556fa19.AST.StatLocalAssign;
import cop5556fa19.AST.StatLocalFunc;
import cop5556fa19.AST.StatRepeat;
import cop5556fa19.AST.StatWhile;
import interpreter.built_ins.print;
import interpreter.built_ins.println;

public abstract class ASTVisitorAdapter implements ASTVisitor {
	
	public int returnFlag = 0;
	public int breakFlag = 0;
	
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
		Exp exp1 = expBin.e0;
		Exp exp2 = expBin.e1;
		Kind k = expBin.op;
		
		//ExpBinary eb = (ExpBinary) e;
		LuaValue exp1val = (LuaValue) exp1.visit(this, arg);
		LuaValue exp2val = (LuaValue) exp2.visit(this, arg);
		//Kind op = eb.op;
		
		if(exp1val.getClass() == LuaInt.class && exp2val.getClass() == LuaInt.class) 
		{
			LuaInt exp1int = (LuaInt) exp1val;
			LuaInt exp2int = (LuaInt) exp2val;
			if(k == OP_PLUS) 
			{
				return new LuaInt(exp1int.v + exp2int.v);
			}
			else if(k == OP_POW)
			{	
				return new LuaInt((int)Math.pow(exp1int.v, exp2int.v));
			}
			else if(k == OP_MINUS)
			{
				return new LuaInt(exp1int.v - exp2int.v);
			}
			else if(k == OP_TIMES) 
			{
				return new LuaInt(exp1int.v * exp2int.v);
			}
			else if(k == OP_DIV)
			{
				return new LuaInt(exp1int.v / exp2int.v);
			}
			else if(k == OP_MOD)
			{
				return new LuaInt(exp1int.v % exp2int.v);
			}
			else if(k == REL_LE)
			{
				return new LuaBoolean(exp1int.v <= exp2int.v);
			}
			else if(k == REL_GE) 
			{
				return new LuaBoolean(exp1int.v >= exp2int.v);
			}
			else if(k == REL_LT) 
			{
				return new LuaBoolean(exp1int.v < exp2int.v);
			}
			else if(k == REL_GT)
			{
				return new LuaBoolean(exp1int.v > exp2int.v);
			}
			else if(k == REL_EQEQ)
			{
				return new LuaBoolean(exp1int.v == exp2int.v);
			}
			else if(k == REL_NOTEQ)
			{
				return new LuaBoolean(exp1int.v != exp2int.v);
			}
			else if(k == OP_DIVDIV)
			{
				return new LuaInt((int) Math.floorDiv(exp1int.v, exp2int.v));
			}
			else if(k == BIT_AMP)
			{
				return new LuaInt(exp1int.v & exp2int.v);
			}
			else if(k == BIT_OR) 
			{
				return new LuaInt(exp1int.v | exp2int.v);
			}
			else if(k == BIT_XOR) 
			{
				return new LuaInt(exp1int.v ^ exp2int.v);
			}
			else if(k == BIT_SHIFTL)
			{
				return new LuaInt(exp1int.v << exp2int.v);
			}
			else if(k == BIT_SHIFTR) 
			{
				return new LuaInt(exp1int.v >> exp2int.v);
			}
		}
		else if(exp1val.getClass() == LuaString.class && exp2val.getClass() == LuaString.class) 
		{
			LuaString exp1String = (LuaString) exp1val;
			LuaString exp2String = (LuaString) exp2val;
			if(k == DOTDOT) 
			{
				return new LuaString(exp1String.value + exp2String.value);
			}
			else if(k == REL_EQEQ) 
			{
				return new LuaBoolean(exp1String.value == exp2String.value);
			}
			else if(k == REL_NOTEQ) 
			{
				return new LuaBoolean(exp1String.value != exp2String.value);
			}
			else if(k == REL_LE) 
			{
				return new LuaBoolean(exp1String.value.charAt(0) <= exp2String.value.charAt(0));
			}
			else if(k == REL_GE) 
			{
				return new LuaBoolean(exp1String.value.charAt(0) >= exp2String.value.charAt(0));
			}
			else if(k == REL_LT) 
			{
				return new LuaBoolean(exp1String.value.charAt(0) < exp2String.value.charAt(0));
			}
			else if(k == REL_GT) 
			{
				return new LuaBoolean(exp1String.value.charAt(0) > exp2String.value.charAt(0));
			}
		}
		
		throw new StaticSemanticException(null, "Failed to evaluate binary expression!");
	}

	@Override
	public Object visitUnExp(ExpUnary unExp, Object arg) throws Exception {
		
		Exp exp1 = unExp.e;
		Kind k = unExp.op;
		
		LuaValue exp1val = (LuaValue) exp1.visit(this, arg);
		
		if(k == OP_MINUS) 
		{
			return new LuaInt(-((LuaInt) exp1val).v);
		}
		else if(k == OP_HASH) 
		{
			if(exp1val.getClass() == LuaInt.class)
			{
				throw new TypeException("Type Exception error for Unary operator # ");
			}
		}
		else if(k == KW_not) 
		{
			if(exp1.getClass() == ExpTrue.class || exp1val.getClass() == LuaInt.class || exp1val.getClass() == LuaString.class) 
			{
				return new LuaBoolean(false);
			}
			else if(exp1.getClass() == ExpFalse.class) 
			{
				return new LuaBoolean(true);
			}
			else 
			{
				throw new StaticSemanticException(null, "Error in unary expression!");
			}
		}
		else if(k == BIT_XOR)
		{
			return new LuaInt((((LuaInt)exp1val).v*-1)-1);
		}
		throw new StaticSemanticException(null, "Failed to evaluate unary expression!");
		//throw new UnsupportedOperationException();
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
		List<Field> f= expTableConstr.fields;
		LuaTable table = new LuaTable();
		//List<LuaValue> tv = new ArrayList<>();
		//tv.add(table);
		for(int i=0; i<f.size(); i++)
		{
			if(f.get(i).getClass() == FieldImplicitKey.class) {
				FieldImplicitKey fik = (FieldImplicitKey) f.get(i);
				
				table.putImplicit((LuaValue)fik.visit(this, arg));
			}
			else if(f.get(i).getClass() == FieldNameKey.class) {
				FieldNameKey fnk = (FieldNameKey) f.get(i);
				Name k = fnk.name;
				Exp v = fnk.exp;
				table.put(k.name, (LuaValue) v.visit(this, arg));
			}
			else if(f.get(i).getClass() == FieldExpKey.class) {
				FieldExpKey fek = (FieldExpKey) f.get(i);
				Exp k = fek.key;
				Exp v = fek.value;
				table.put((LuaValue) k.visit(this, arg), (LuaValue) v.visit(this, arg));
			}
		}
		
		return table;
		//throw new UnsupportedOperationException();
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
		//System.out.println("In visit block");
		List<LuaValue> blockValue = new ArrayList<>();
		List<Stat> s = block.stats;
		if(s.size() == 0)
		{
			return null;
		}
		for(int i=0; i<s.size(); i++)
		{
			if(s.get(i).getClass() == StatBreak.class)
			{
				s.get(i).visit(this, arg);
				break;
			}
			if(s.get(i).getClass() == RetStat.class && returnFlag==0 && breakFlag ==0)
			{
				returnFlag=1;
				blockValue.addAll((List<LuaValue>) s.get(i).visit(this, arg));
				break;
			}
			if(returnFlag==0 && s.get(i).getClass() != StatAssign.class && breakFlag ==0)
				blockValue.addAll((List<LuaValue>) s.get(i).visit(this, arg));
				
			if(s.get(i).getClass() == StatAssign.class && breakFlag ==0)
				s.get(i).visit(this, arg);
		}
	    return blockValue;
	}

	@Override
	public Object visitStatBreak(StatBreak statBreak, Object arg, Object arg2) {
		breakFlag =1;
		List<LuaValue> noReturn = new ArrayList<>();
		return noReturn;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatBreak(StatBreak statBreak, Object arg) throws Exception {
		breakFlag =1;
		List<LuaValue> noReturn = new ArrayList<>();
		return noReturn;
		//throw new UnsupportedOperationException();
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
		Exp whileE = statWhile.e;
		Block whileB = statWhile.b;
		List<LuaValue> bval = new ArrayList<>();
		
		while(((LuaBoolean) whileE.visit(this, arg)).value)
		{
			bval = (List<LuaValue>) whileB.visit(this, arg);
			if(breakFlag == 1)
			{
				break;
			}
		}
		breakFlag = 0;
		return bval;
		//throw new UnsupportedOperationException();
	}

	@Override
	public Object visitStatRepeat(StatRepeat statRepeat, Object arg) throws Exception {
		
		Exp repeatE = statRepeat.e;
		Block repeatB = statRepeat.b;
		List<LuaValue> bval = new ArrayList<>();
		
		bval = (List<LuaValue>) repeatB.visit(this, arg);
		while(!((LuaBoolean) repeatE.visit(this, arg)).value)
		{
			if(breakFlag == 1)
			{
				break;
			}
			bval = (List<LuaValue>) repeatB.visit(this, arg);
		}
		breakFlag=0;
		return bval;
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
		//System.out.println("In visit stat if");
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
		//System.out.println("In visit ret stat");
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
		//System.out.println("In visit chunk");
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
		Exp e = fieldImplicitKey.exp;
		LuaValue eval = (LuaValue) e.visit(this, arg);
		return eval;
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
		
		//System.out.println("In visit stat assign");
		List<Exp> vList = statAssign.varList;
		List<Exp> eList = statAssign.expList;
		Exp v,e;
		LuaString key = null;
		LuaValue eval = null;
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
		Exp funcE = expFunctionCall.f;
		List<Exp> funcArgs = expFunctionCall.args;
		LuaValue eval = null;
		List<LuaValue> arglist = new ArrayList<>();
		eval = (LuaValue) funcE.visit(this, arg);
		for(int i=0; i<funcArgs.size(); i++)
		{
			arglist.add((LuaValue) funcArgs.get(i).visit(this, arg));
		}
		if(eval.getClass() == print.class)
		{
			for (LuaValue v: arglist) {
				System.out.print(v);
			}
			return null;
		}
		if(eval.getClass() == println.class)
		{
			for (LuaValue v: arglist) {
				System.out.println(v);
			}
			return null;
		}
		
		throw new StaticSemanticException(null, "Unsupported function call!");
		//throw new UnsupportedOperationException();
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

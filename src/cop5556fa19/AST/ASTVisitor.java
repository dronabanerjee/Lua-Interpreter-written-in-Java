package cop5556fa19.AST;


public interface ASTVisitor {

	Object visitExpNil(ExpNil expNil, Object arg) throws Exception;

	Object visitExpBin(ExpBinary expBin, Object arg) throws Exception;

	Object visitUnExp(ExpUnary unExp, Object arg) throws Exception;

	Object visitExpInt(ExpInt expInt, Object arg) throws Exception;

	Object visitExpString(ExpString expString, Object arg) throws Exception;

	Object visitExpTable(ExpTable expTableConstr, Object arg) throws Exception;

	Object visitExpList(ExpList expList, Object arg) throws Exception;

	Object visitParList(ParList parList, Object arg) throws Exception;

	Object visitFunDef(ExpFunction funcDec, Object arg) throws Exception;

	Object visitName(Name name, Object arg) throws Exception;

	Object visitBlock(Block block, Object arg) throws Exception;

	Object visitStatBreak(StatBreak statBreak, Object arg, Object arg2);

	Object visitStatBreak(StatBreak statBreak, Object arg) throws Exception;

	Object visitStatGoto(StatGoto statGoto, Object arg) throws Exception;

	Object visitStatDo(StatDo statDo, Object arg) throws Exception;

	Object visitStatWhile(StatWhile statWhile, Object arg) throws Exception;

	Object visitStatRepeat(StatRepeat statRepeat, Object arg) throws Exception;

	Object visitStatIf(StatIf statIf, Object arg) throws Exception;

	Object visitStatFor(StatFor statFor1, Object arg) throws Exception;

	Object visitStatForEach(StatForEach statForEach, Object arg) throws Exception;

	Object visitFuncName(FuncName funcName, Object arg) throws Exception;

	Object visitStatFunction(StatFunction statFunction, Object arg) throws Exception;

	Object visitStatLocalFunc(StatLocalFunc statLocalFunc, Object arg) throws Exception;

	Object visitStatLocalAssign(StatLocalAssign statLocalAssign, Object arg) throws Exception;

	Object visitRetStat(RetStat retStat, Object arg) throws Exception;

	Object visitChunk(Chunk chunk, Object arg) throws Exception;

	Object visitFieldExpKey(FieldExpKey fieldExpKey, Object arg) throws Exception;

	Object visitFieldNameKey(FieldNameKey fieldNameKey, Object arg) throws Exception;

	Object visitFieldImplicitKey(FieldImplicitKey fieldImplicitKey, Object arg) throws Exception;

	Object visitExpTrue(ExpTrue expTrue, Object arg) throws Exception;

	Object visitExpFalse(ExpFalse expFalse, Object arg) throws Exception;

	Object visitFuncBody(FuncBody funcBody, Object arg) throws Exception;

	Object visitExpVarArgs(ExpVarArgs expVarArgs, Object arg) throws Exception;

	Object visitStatAssign(StatAssign statAssign, Object arg) throws Exception;

	Object visitExpTableLookup(ExpTableLookup expTableLookup, Object arg) throws Exception;

	Object visitExpFunctionCall(ExpFunctionCall expFunctionCall, Object arg) throws Exception;

	Object visitLabel(StatLabel statLabel, Object ar) throws Exception;

	Object visitFieldList(FieldList fieldList, Object arg) throws Exception;

	Object visitExpName(ExpName expName, Object arg) throws Exception;

}

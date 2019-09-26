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

package cop5556fa19.AST;

public interface ASTVisitor {

	Object visitExpNil(ExpNil expNil, Object arg);

	Object visitExpBin(ExpBinary expBin, Object arg);

	Object visitUnExp(ExpUnary unExp, Object arg);

	Object visitExpInt(ExpInt expInt, Object arg);

	Object visitExpString(ExpString expString, Object arg);

	Object visitFieldList(FieldList fieldList, Object arg);

	Object visitExpTableConstr(ExpTable expTableConstr, Object arg);

	Object visitExpIdent(ExpName expIdent, Object arg);

	Object visitFunctionCall(FunctionCall functionCall, Object arg);

	Object visitExpList(ExpList expList, Object arg);

	Object visitParList(ParList parList, Object arg);

	Object visitFunDef(ExpFunction funcDec, Object arg);

	Object visitName(Name name, Object arg);

	Object visitFuncName(FuncName funcName, Object arg);

	Object visitFieldExpKey(FieldExpKey fieldExpKey, Object object);

	Object visitFieldNameKey(FieldNameKey fieldNameKey, Object arg);

	Object visitFieldImplicitKey(FieldImplicitKey fieldImplicitKey, Object arg);

	Object visitExpTrue(ExpTrue expTrue, Object arg);

	Object visitExpFalse(ExpFalse expFalse, Object arg);

	Object visitFuncBody(FuncBody funcBody, Object arg);

	Object visitExpVarArgs(ExpVarArgs expVarArgs, Object arg);

	Object visitBlock(Block block, Object arg);

}

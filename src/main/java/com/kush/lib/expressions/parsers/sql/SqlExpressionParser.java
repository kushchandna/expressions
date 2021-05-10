package com.kush.lib.expressions.parsers.sql;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.ExpressionParser;
import com.kush.lib.expressions.ExpressionParsingFailedException;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

public class SqlExpressionParser implements ExpressionParser<String> {

    private final ExpressionFactory expressionFactory;

    public SqlExpressionParser(ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    public Expression parse(String input) throws ExpressionParsingFailedException {
        net.sf.jsqlparser.expression.Expression jsqlExpr = jsqlParse(input);
        return JsqlExpressionAdapter.adapt(jsqlExpr, expressionFactory);
    }

    private net.sf.jsqlparser.expression.Expression jsqlParse(String input) throws ExpressionParsingFailedException {
        try {
            return CCJSqlParserUtil.parseCondExpression(input, false);
        } catch (JSQLParserException e) {
            throw new ExpressionParsingFailedException("Failed to parse SQL", e);
        }
    }
}

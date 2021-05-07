package com.kush.lib.expressions.parsers.sql;

import java.util.ArrayList;
import java.util.List;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionFactory;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NotExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.schema.Column;

public class JsqlExpressionAdapter extends ExpressionVisitorAdapter {

    private final ExpressionFactory expressionFactory;

    private final List<Expression> expressions = new ArrayList<>();

    public JsqlExpressionAdapter(ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    public static Expression adapt(net.sf.jsqlparser.expression.Expression expr, ExpressionFactory exprFactory) {
        JsqlExpressionAdapter adapter = new JsqlExpressionAdapter(exprFactory);
        expr.accept(adapter);
        return adapter.get().get(0);
    }

    private static List<Expression> adapt(ItemsList itemsList, ExpressionFactory exprFactory) {
        JsqlExpressionAdapter adapter = new JsqlExpressionAdapter(exprFactory);
        itemsList.accept(adapter);
        return adapter.get();
    }

    @Override
    public void visit(net.sf.jsqlparser.expression.operators.conditional.AndExpression expr) {
        processBinaryExpression(expr, expressionFactory::createAndExpression);
    }

    @Override
    public void visit(net.sf.jsqlparser.expression.operators.conditional.OrExpression expr) {
        processBinaryExpression(expr, expressionFactory::createOrExpression);
    }

    @Override
    public void visit(EqualsTo expr) {
        processBinaryExpression(expr, expressionFactory::createEqualsExpression);
    }

    @Override
    public void visit(InExpression expr) {
        add(expressionFactory.createInExpression(adapt(expr.getLeftExpression()), adapt(expr.getRightItemsList())));
    }

    @Override
    public void visit(GreaterThan expr) {
        processBinaryExpression(expr, expressionFactory::createGreaterThanExpression);
    }

    @Override
    public void visit(GreaterThanEquals expr) {
        processBinaryExpression(expr, expressionFactory::createGreaterThanEqualsExpression);
    }

    @Override
    public void visit(MinorThan expr) {
        processBinaryExpression(expr, expressionFactory::createLessThanExpression);
    }

    @Override
    public void visit(MinorThanEquals expr) {
        processBinaryExpression(expr, expressionFactory::createLessThanEqualsExpression);
    }

    @Override
    public void visit(NotExpression notExpr) {
        add(expressionFactory.createNotExpression(adapt(notExpr.getExpression())));
    }

    @Override
    public void visit(StringValue value) {
        add(expressionFactory.createConstantStringExpression(value.getValue()));
    }

    // TODO handle long values
    @Override
    public void visit(LongValue value) {
        add(expressionFactory.createConstantIntExpression((int) value.getValue()));
    }

    @Override
    public void visit(Column column) {
        add(expressionFactory.createFieldExpression(column.getFullyQualifiedName()));
    }

    private void processBinaryExpression(net.sf.jsqlparser.expression.Expression expr,
            BinaryExpressionAdapter binaryExprAdapter) {
        add(binaryExprAdapter.adapt(
                adapt(binary(expr).getLeftExpression()),
                adapt(binary(expr).getRightExpression())));
    }

    private BinaryExpression binary(net.sf.jsqlparser.expression.Expression expr) {
        return (BinaryExpression) expr;
    }

    private Expression adapt(net.sf.jsqlparser.expression.Expression expr) {
        return adapt(expr, expressionFactory);
    }

    private List<Expression> adapt(ItemsList itemsList) {
        return adapt(itemsList, expressionFactory);
    }

    private List<Expression> get() {
        return expressions;
    }

    private void add(Expression expression) {
        expressions.add(expression);
    }

    private interface BinaryExpressionAdapter {

        Expression adapt(Expression left, Expression right);
    }
}

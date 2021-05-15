package com.kush.lib.expressions.utils;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.ExpressionFactory;
import com.kush.lib.expressions.ExpressionParsingFailedException;
import com.kush.lib.expressions.aspect.Aspect;
import com.kush.lib.expressions.aspect.AspectFieldEvaluationFactory;
import com.kush.lib.expressions.aspect.Aspects;
import com.kush.lib.expressions.evaluators.DefaultExpressionEvaluatorFactory;
import com.kush.lib.expressions.evaluators.FieldExpressionEvaluatorFactory;
import com.kush.lib.expressions.factory.DefaultExpressionFactory;
import com.kush.lib.expressions.functions.FunctonsRepository;
import com.kush.lib.expressions.functions.reflection.ClassFunctionsRepository;
import com.kush.lib.expressions.parsers.sql.SqlExpressionParser;
import com.kush.lib.expressions.types.TypedValue;

public class ExpressionUtils {

    public static Expression parseSql(String sql) throws ExpressionParsingFailedException {
        ExpressionFactory exprFactory = new DefaultExpressionFactory();
        SqlExpressionParser parser = new SqlExpressionParser(exprFactory);
        return parser.parse(sql);
    }

    public static TypedValue evaluateConstantExpression(String sql) throws ExpressionException {
        ExpressionEvaluatorFactory<?> evaluatorFactory = getExpressionEvaluatorFactory(null);
        ExpressionEvaluator<?> evaluator = evaluatorFactory.create(parseSql(sql));
        return evaluator.evaluate(null);
    }

    public static <T> ExpressionEvaluator<T> getEvaluator(Expression expression, Class<T> aspectClass,
            Class<?>... functionClasses) throws ExpressionException {
        return getEvaluator(expression, Aspects.classBased(aspectClass), functionClasses);
    }

    public static <T> ExpressionEvaluator<T> getEvaluator(Expression expression, Aspect<T> aspect, Class<?>... functionClasses)
            throws ExpressionException {
        ExpressionEvaluatorFactory<T> exprEvalFactory = getEvaluatorFactory(aspect, functionClasses);
        return exprEvalFactory.create(expression);
    }

    public static <T> ExpressionEvaluatorFactory<T> getEvaluatorFactory(Class<T> aspectClass, Class<?>... functionClasses)
            throws ExpressionException {
        return getEvaluatorFactory(Aspects.classBased(aspectClass), functionClasses);
    }

    public static <T> ExpressionEvaluatorFactory<T> getEvaluatorFactory(Aspect<T> aspect, Class<?>... functionClasses) {
        FieldExpressionEvaluatorFactory<T> fieldEvalFactory = new AspectFieldEvaluationFactory<>(aspect);
        return getExpressionEvaluatorFactory(fieldEvalFactory, functionClasses);
    }

    public static boolean isConstant(Expression expression, ExpressionEvaluatorFactory<?> evalFactory)
            throws ExpressionException {
        ExpressionEvaluator<?> evaluator = evalFactory.create(expression);
        return evaluator.getConstantValue().isPresent();
    }

    private static <T> ExpressionEvaluatorFactory<T> getExpressionEvaluatorFactory(
            FieldExpressionEvaluatorFactory<T> fieldEvalFactory,
            Class<?>... functionClasses) {
        FunctonsRepository functionsRepository = ClassFunctionsRepository.using(functionClasses);
        return new DefaultExpressionEvaluatorFactory<>(fieldEvalFactory, functionsRepository);
    }
}

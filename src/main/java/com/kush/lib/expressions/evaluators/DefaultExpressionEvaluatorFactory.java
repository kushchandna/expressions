package com.kush.lib.expressions.evaluators;

import com.kush.lib.expressions.Expression;
import com.kush.lib.expressions.ExpressionEvaluator;
import com.kush.lib.expressions.ExpressionEvaluatorFactory;
import com.kush.lib.expressions.ExpressionException;
import com.kush.lib.expressions.ExpressionHandler;
import com.kush.lib.expressions.clauses.AdditionExpression;
import com.kush.lib.expressions.clauses.AndExpression;
import com.kush.lib.expressions.clauses.CaseExpression;
import com.kush.lib.expressions.clauses.ConstantIntExpression;
import com.kush.lib.expressions.clauses.ConstantStringExpression;
import com.kush.lib.expressions.clauses.DivisionExpression;
import com.kush.lib.expressions.clauses.EqualsExpression;
import com.kush.lib.expressions.clauses.FieldExpression;
import com.kush.lib.expressions.clauses.FunctionExpression;
import com.kush.lib.expressions.clauses.GreaterThanEqualsExpression;
import com.kush.lib.expressions.clauses.GreaterThanExpression;
import com.kush.lib.expressions.clauses.InExpression;
import com.kush.lib.expressions.clauses.LessThanEqualsExpression;
import com.kush.lib.expressions.clauses.LessThanExpression;
import com.kush.lib.expressions.clauses.MultiplicationExpression;
import com.kush.lib.expressions.clauses.NotExpression;
import com.kush.lib.expressions.clauses.OrExpression;
import com.kush.lib.expressions.clauses.SubtractionExpression;
import com.kush.lib.expressions.functions.FunctonsRepository;

public class DefaultExpressionEvaluatorFactory<T> implements ExpressionEvaluatorFactory<T> {

    private final InternalExpressionEvaluatorFactory internalFactory;

    public DefaultExpressionEvaluatorFactory(FieldExpressionEvaluatorFactory<T> fieldEvaluatorFactory,
            FunctonsRepository functonsRepository) {
        internalFactory = new InternalExpressionEvaluatorFactory(fieldEvaluatorFactory, functonsRepository);
    }

    @Override
    public ExpressionEvaluator<T> create(Expression expression) throws ExpressionException {
        return internalFactory.accept(expression);
    }

    private class InternalExpressionEvaluatorFactory extends ExpressionHandler<ExpressionEvaluator<T>> {

        private final FieldExpressionEvaluatorFactory<T> fieldEvaluatorFactory;
        private final FunctonsRepository functonsRepository;

        public InternalExpressionEvaluatorFactory(FieldExpressionEvaluatorFactory<T> fieldEvaluatorFactory,
                FunctonsRepository functonsRepository) {
            this.fieldEvaluatorFactory = fieldEvaluatorFactory;
            this.functonsRepository = functonsRepository;
        }

        @Override
        protected ExpressionEvaluator<T> handle(FieldExpression expression) throws ExpressionException {
            return fieldEvaluatorFactory.create(expression);
        }

        @Override
        protected ExpressionEvaluator<T> handle(AndExpression expression) throws ExpressionException {
            return new AndExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(OrExpression expression) throws ExpressionException {
            return new OrExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(NotExpression expression) throws ExpressionException {
            return new NotExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(EqualsExpression expression) throws ExpressionException {
            return new EqualsExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(InExpression expression) throws ExpressionException {
            return new InExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(GreaterThanExpression expression) throws ExpressionException {
            return new GreaterThanExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(GreaterThanEqualsExpression expression) throws ExpressionException {
            return new GreaterThanEqualsExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(LessThanExpression expression) throws ExpressionException {
            return new LessThanExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(LessThanEqualsExpression expression) throws ExpressionException {
            return new LessThanEqualsExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(FunctionExpression expression) throws ExpressionException {
            return new FunctionExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this, functonsRepository);
        }

        @Override
        protected ExpressionEvaluator<T> handle(ConstantStringExpression expression) {
            return new ConstantStringExpressionEvaluator<>(expression);
        }

        @Override
        protected ExpressionEvaluator<T> handle(ConstantIntExpression expression) {
            return new ConstantIntExpressionEvaluator<>(expression);
        }

        @Override
        protected ExpressionEvaluator<T> handle(AdditionExpression expression) throws ExpressionException {
            return new AdditionExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(SubtractionExpression expression) throws ExpressionException {
            return new SubtractionExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(MultiplicationExpression expression) throws ExpressionException {
            return new MultiplicationExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(DivisionExpression expression) throws ExpressionException {
            return new DivisionExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }

        @Override
        protected ExpressionEvaluator<T> handle(CaseExpression expression) throws ExpressionException {
            return new CaseExpressionEvaluator<>(expression, DefaultExpressionEvaluatorFactory.this);
        }
    }
}

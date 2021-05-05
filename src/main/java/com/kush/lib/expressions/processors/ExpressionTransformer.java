package com.kush.lib.expressions.processors;

import static com.kush.commons.utils.ObjectUtils.nullOrElse;

import com.kush.lib.expressions.Expression;

public interface ExpressionTransformer {

    Expression transform(Expression expression);

    default <E extends Expression> E transform(Expression expression, Class<E> resultType) {
        return nullOrElse(transform(expression), resultType::cast);
    }
}

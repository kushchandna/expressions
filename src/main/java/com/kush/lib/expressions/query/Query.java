package com.kush.lib.expressions.query;

import java.util.Collection;

import com.kush.lib.expressions.Expression;

public interface Query {

    Collection<Expression> getSelections();

    String getSource();

    Expression getFilter();

    Collection<Expression> getGroupings();

    Collection<Expression> getGroupFilter();
}

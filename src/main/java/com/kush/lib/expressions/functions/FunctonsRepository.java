package com.kush.lib.expressions.functions;

import java.util.Optional;

public interface FunctonsRepository {

    FunctonsRepository EMPTY = name -> Optional.empty();

    Optional<FunctionSpec> getFunctionSpec(String functionName);
}

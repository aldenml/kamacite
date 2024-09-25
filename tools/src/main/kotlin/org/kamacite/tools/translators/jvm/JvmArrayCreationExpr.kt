/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.translators.JavaArrayCreationExpr

class JvmArrayCreationExpr(
    expr: ArrayCreationExpr,
) : JavaArrayCreationExpr(expr), JvmTranslator {

    override fun newArray(elementType: PrimitiveType, size: Int): String {
        val tr = findFor(elementType)
        val tp = tr.translate()
        return "new $tp[$size];"
    }
}

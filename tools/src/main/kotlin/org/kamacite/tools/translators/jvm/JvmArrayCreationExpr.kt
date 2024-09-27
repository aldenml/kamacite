/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayCreationExpr
import org.kamacite.tools.translators.JavaArrayCreationExpr

class JvmArrayCreationExpr(
    expr: ArrayCreationExpr,
) : JavaArrayCreationExpr(expr), JvmTranslator {

    override fun newArray(): String {
        val elementType = expr.elementType
        val size = expr.levels[0].dimension.get().toString().toInt()

        val tr = findFor(elementType)
        val tp = tr.translate()

        return "new $tp[$size]"
    }
}

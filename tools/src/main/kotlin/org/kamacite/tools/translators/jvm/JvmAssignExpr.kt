/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.AssignExpr
import org.kamacite.tools.translators.JavaAssignExpr

class JvmAssignExpr(
    expr: AssignExpr,
) : JavaAssignExpr(expr), JvmTranslator {

    override fun assignToArray(name: String, index: String): String {
        return "$name[$index]"
    }
}

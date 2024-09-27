/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayAccessExpr
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import org.kamacite.tools.translators.JavaAssignExpr

class JvmAssignExpr(
    expr: AssignExpr,
) : JavaAssignExpr(expr), JvmTranslator {

    override fun assignExpression(): String {
        val sb = StringBuilder()

        val target = expr.target
        when (target) {

            is ArrayAccessExpr -> {
                val name = target.name.toString()
                val index = target.index.toString()
                val s = "$name[$index]"
                sb.append(s)
            }

            else -> sb.append(target)
        }

        sb.append(" = ")

        val valueExpr = expr.value
        val value = when (valueExpr) {

            is MethodCallExpr -> findFor(valueExpr).translate()

            else -> valueExpr.toString()
        }

        sb.append(value).append(';')

        return sb.toString()
    }
}

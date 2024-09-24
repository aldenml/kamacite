/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayAccessExpr
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.CharLiteralExpr
import org.kamacite.tools.CodeUnsupportedException

class JvmAssignExpr(
    val expr: AssignExpr,
) {

    fun translate(): String {
        val sb = StringBuilder()

        val target = expr.target
        when (target) {

            is ArrayAccessExpr -> sb.append(target)

            else -> CodeUnsupportedException(expr)
        }

        val value = expr.value
        when (value) {

            is CharLiteralExpr -> sb.append(" = ").append(value)

            else -> CodeUnsupportedException(expr)
        }

        return sb.toString()
    }
}

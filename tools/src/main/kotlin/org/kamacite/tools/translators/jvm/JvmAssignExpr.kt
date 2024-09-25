/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayAccessExpr
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.CastExpr
import com.github.javaparser.ast.expr.CharLiteralExpr
import com.github.javaparser.ast.expr.EnclosedExpr
import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.NameExpr
import org.kamacite.tools.CodeUnsupportedException

class JvmAssignExpr(
    val expr: AssignExpr,
) : JvmTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        val target = expr.target
        when (target) {

            is ArrayAccessExpr -> sb.append(target)

            is NameExpr -> sb.append(target)

            else -> throw CodeUnsupportedException(expr)
        }

        val value = expr.value
        when (value) {

            is CharLiteralExpr -> sb.append(" = ").append(value)

            is MethodCallExpr -> sb.append(" = ").append(JvmMethodCallExpr(value).translate())

            is CastExpr -> sb.append(" = ").append(value)

            is BinaryExpr -> sb.append(" = ").append(value)

            is NameExpr -> sb.append(" = ").append(value)

            is EnclosedExpr -> sb.append(" = ").append(value)

            is IntegerLiteralExpr -> sb.append(" = ").append(value)

            else -> throw CodeUnsupportedException(expr)
        }

        return sb.toString()
    }
}

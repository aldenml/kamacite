/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

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

abstract class JavaAssignExpr(
    val expr: AssignExpr,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        val target = expr.target
        when (target) {

            is ArrayAccessExpr -> {
                if (!target.name.isNameExpr)
                    throw CodeUnsupportedException(expr)
                if (!target.index.isNameExpr && !target.index.isIntegerLiteralExpr)
                    throw CodeUnsupportedException(expr)
            }

            is NameExpr -> Unit

            else -> throw CodeUnsupportedException(expr)
        }

        val value = expr.value
        when (value) {

            is CharLiteralExpr ->
                if (!isUtilOrTestCode(expr))
                    throw CodeUnsupportedException(expr)

            is MethodCallExpr -> Unit

            is CastExpr -> Unit

            is BinaryExpr -> Unit

            is NameExpr -> Unit

            is EnclosedExpr -> Unit

            is IntegerLiteralExpr -> Unit

            else -> throw CodeUnsupportedException(expr)
        }

        sb.append(assignExpression())

        return sb.toString()
    }

    abstract fun assignExpression(): String
}

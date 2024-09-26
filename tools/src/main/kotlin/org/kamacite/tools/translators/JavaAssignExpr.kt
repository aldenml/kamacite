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

                val name = target.name.toString()
                val index = target.index.toString()
                val s = assignToArray(name, index)
                sb.append(s)
            }

            is NameExpr -> sb.append(target)

            else -> throw CodeUnsupportedException(expr)
        }

        sb.append(" = ")

        val valueExpr = expr.value
        val value = when (valueExpr) {

            is CharLiteralExpr -> valueExpr.toString()

            is MethodCallExpr -> findFor(valueExpr).translate()

            is CastExpr -> valueExpr.toString()

            is BinaryExpr -> valueExpr.toString()

            is NameExpr -> valueExpr.toString()

            is EnclosedExpr -> valueExpr.toString()

            is IntegerLiteralExpr -> valueExpr.toString()

            else -> throw CodeUnsupportedException(expr)
        }

        sb.append(value).append(';')

        return sb.toString()
    }

    abstract fun assignToArray(name: String, index: String): String
}

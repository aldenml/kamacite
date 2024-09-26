/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.StringLiteralExpr
import com.github.javaparser.ast.expr.UnaryExpr
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaMethodCallExpr(
    val methodCall: MethodCallExpr
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        sb.append(methodCall.nameAsString)
            .append('(')

        val args = methodCall.arguments.joinToString { arg ->
            when (arg) {

                is NameExpr -> arg.name.toString()

                is IntegerLiteralExpr -> arg.value.toString()

                is UnaryExpr -> {
                    if (!arg.expression.isIntegerLiteralExpr)
                        throw CodeUnsupportedException(methodCall)
                    arg.toString()
                }

                is StringLiteralExpr -> {
                    if (!fileName(methodCall).contains("Test"))
                        throw CodeUnsupportedException(methodCall)
                    "\"${arg.value}\""
                }

                else -> throw CodeUnsupportedException(methodCall)
            }
        }

        sb.append(args)
            .append(");")

        return sb.toString()
    }
}

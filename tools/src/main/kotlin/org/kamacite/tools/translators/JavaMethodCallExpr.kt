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

        methodCall.arguments.forEach { arg ->
            when (arg) {

                is NameExpr -> Unit //

                is IntegerLiteralExpr -> Unit

                is UnaryExpr -> {
                    if (!arg.expression.isIntegerLiteralExpr)
                        throw CodeUnsupportedException(methodCall)
                }

                is StringLiteralExpr -> {
                    if (!isTestCode(methodCall))
                        throw CodeUnsupportedException(methodCall)
                }

                else -> throw CodeUnsupportedException(methodCall)
            }
        }

        sb.append(invokeMethod())

        return sb.toString()
    }

    abstract fun invokeMethod(): String
}

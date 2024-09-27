/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.expr.ArrayAccessExpr
import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaVariableDeclarationExpr(
    val expr: VariableDeclarationExpr,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        if (expr.variables.count() != 1)
            throw CodeUnsupportedException(expr)

        val variable = expr.variables[0]

        if (variable.initializer.isEmpty)
            throw CodeUnsupportedException(expr)

        val initializer = variable.initializer.get()

        val type = expr.commonType
        when (type) {

            is ArrayType ->
                if (variable.initializer.isEmpty ||
                    variable.initializer.get() !is ArrayCreationExpr
                )
                    throw CodeUnsupportedException(expr)

            is PrimitiveType -> Unit
        }

        when (initializer) {

            is ArrayCreationExpr -> Unit

            is IntegerLiteralExpr -> Unit

            is MethodCallExpr -> Unit

            is ArrayAccessExpr -> Unit

            is BinaryExpr -> Unit

            else -> throw CodeUnsupportedException(expr)
        }

        sb.append(declareVariable())

        return sb.toString()
    }

    abstract fun declareVariable(): String
}

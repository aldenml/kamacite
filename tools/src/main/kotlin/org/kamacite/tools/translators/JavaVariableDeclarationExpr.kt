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

            is ArrayType -> {
                if (variable.initializer.isPresent &&
                    variable.initializer.get() is ArrayCreationExpr
                ) {
                    val s = declareArrayVariable(type, variable.name.toString())
                    sb.append(s)
                } else
                    throw CodeUnsupportedException(expr)
            }

            is PrimitiveType -> {
                val s = declareVariable(type, variable.name.toString())
                sb.append(s)
            }
        }

        sb.append(" = ")

        val value = when (initializer) {

            is ArrayCreationExpr -> findFor(initializer).translate()

            is IntegerLiteralExpr -> initializer.value.toString()

            is MethodCallExpr -> findFor(initializer).translate()

            is ArrayAccessExpr -> initializer.toString()

            is BinaryExpr -> initializer.toString()

            else -> throw CodeUnsupportedException(expr)
        }

        sb.append(value).append(';')

        return sb.toString()
    }

    abstract fun declareArrayVariable(type: ArrayType, name: String): String

    abstract fun declareVariable(type: PrimitiveType, name: String): String
}

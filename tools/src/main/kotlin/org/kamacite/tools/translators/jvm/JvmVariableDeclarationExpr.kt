/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

class JvmVariableDeclarationExpr(
    val expr: VariableDeclarationExpr,
) {

    fun translate(): String {
        val sb = StringBuilder()

        if (expr.variables.count() != 1)
            throw CodeUnsupportedException(expr)

        val type = expr.commonType
        when (type) {

            is ArrayType ->
                sb.append(JvmArrayType(type).translate()).append(' ')

            is PrimitiveType ->
                sb.append(JvmPrimitiveType(type).translate()).append(' ')
        }

        val variable = expr.variables[0]
        sb.append(variable.name).append(" = ")

        val initializer = variable.initializer.get()
        when (initializer) {

            is ArrayCreationExpr -> sb.append(JvmArrayCreationExpr(initializer).translate())

            is IntegerLiteralExpr -> sb.append(initializer.value).append(';')

            else -> throw CodeUnsupportedException(expr)
        }

        return sb.toString()
    }
}

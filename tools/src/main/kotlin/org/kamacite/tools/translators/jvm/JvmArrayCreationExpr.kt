/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

class JvmArrayCreationExpr(
    val expr: ArrayCreationExpr,
) {

    fun translate(): String {
        val sb = StringBuilder()

        if (expr.levels.count() != 1)
            throw CodeUnsupportedException(expr)

        val elementType = expr.elementType
        val size = expr.levels[0].dimension.get()

        when (elementType) {

            is PrimitiveType ->
                sb.append("new ")
                    .append(JvmPrimitiveType(elementType).translate())
                    .append('[')
                    .append(size)
                    .append(']')
                    .append(';')

            else -> throw CodeUnsupportedException(expr)
        }

        return sb.toString()
    }
}

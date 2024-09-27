/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.PrimitiveType.Primitive.BYTE
import com.github.javaparser.ast.type.PrimitiveType.Primitive.CHAR
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaArrayCreationExpr(
    val expr: ArrayCreationExpr,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        val levels = expr.levels
        if (levels.count() != 1 || levels[0].dimension.isEmpty)
            throw CodeUnsupportedException(expr)

        val type = expr.elementType
        when (type) {

            is PrimitiveType -> {
                if (type.type == CHAR && !isUtilOrTestCode(expr))
                    throw CodeUnsupportedException(type)

                if (type.type != BYTE && type.type != CHAR)
                    throw CodeUnsupportedException(type)
            }

            else -> throw CodeUnsupportedException(type)
        }

        val s = newArray()
        sb.append(s)

        return sb.toString()
    }

    abstract fun newArray(): String
}

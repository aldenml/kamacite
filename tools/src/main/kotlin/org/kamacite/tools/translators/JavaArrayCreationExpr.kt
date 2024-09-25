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
import com.github.javaparser.ast.type.Type
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaArrayCreationExpr(
    val expr: ArrayCreationExpr,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        if (expr.levels.count() != 1)
            throw CodeUnsupportedException(expr)

        val elementType = expr.elementType
        val size = expr.levels[0].dimension.get().toString().toInt()

        validateElementType(elementType)

        val s = newArray(elementType.asPrimitiveType(), size)
        sb.append(s)

        return sb.toString()
    }

    abstract fun newArray(elementType: PrimitiveType, size: Int): String

    private fun validateElementType(type: Type) {
        when (type) {

            is PrimitiveType ->
                if (type.type != CHAR && type.type != BYTE)
                    throw CodeUnsupportedException(type)

            else -> throw CodeUnsupportedException(type)
        }
    }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.PrimitiveType.Primitive.BYTE
import com.github.javaparser.ast.type.PrimitiveType.Primitive.CHAR
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaArrayType(
    val type: ArrayType,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        if (type.arrayLevel != 1)
            throw CodeUnsupportedException(type)

        val type = type.elementType
        when (type) {

            is PrimitiveType -> {
                if (type.type == CHAR && !isUtilOrTestCode(type))
                    throw CodeUnsupportedException(type)

                if (type.type != BYTE && type.type != CHAR)
                    throw CodeUnsupportedException(type)
            }

            else -> throw CodeUnsupportedException(type)
        }

        val s = declareArray()
        sb.append(s)

        return sb.toString()
    }

    abstract fun declareArray(): String
}

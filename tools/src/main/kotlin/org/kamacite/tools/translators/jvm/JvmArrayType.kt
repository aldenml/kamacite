/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

class JvmArrayType(
    val type: ArrayType,
) {

    fun translate(): String {
        val sb = StringBuilder()

        if (type.arrayLevel != 1)
            throw CodeUnsupportedException(type)

        val elementType = type.elementType

        when (elementType) {

            is PrimitiveType ->
                sb.append(JvmPrimitiveType(elementType).translate())
                    .append("[]")

            else -> throw CodeUnsupportedException(type)
        }

        return sb.toString()
    }
}

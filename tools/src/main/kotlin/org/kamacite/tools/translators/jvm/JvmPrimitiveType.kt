/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.PrimitiveType.Primitive.BYTE
import com.github.javaparser.ast.type.PrimitiveType.Primitive.CHAR
import com.github.javaparser.ast.type.PrimitiveType.Primitive.INT
import org.kamacite.tools.CodeUnsupportedException

class JvmPrimitiveType(
    val type: PrimitiveType,
) : JvmTranslator {

    override fun translate(): String = when (type.type) {
        BYTE -> "byte"
        INT -> "int"
        CHAR -> "char"
        else -> throw CodeUnsupportedException(type)
    }
}

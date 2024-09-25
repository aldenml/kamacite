/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.translators.JavaArrayType

class JvmArrayType(
    type: ArrayType,
) : JavaArrayType(type), JvmTranslator {

    override fun declareArray(elementType: PrimitiveType): String {
        val tr = findFor(elementType)
        val tp = tr.translate()
        return "$tp[]"
    }
}

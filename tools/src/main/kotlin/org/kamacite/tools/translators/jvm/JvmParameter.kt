/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException
import org.kamacite.tools.translators.JavaParameter

class JvmParameter(
    parameter: Parameter,
) : JavaParameter(parameter), JvmTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        val type = parameter.type
        when (type) {

            is ArrayType -> {
                val tr = findFor(type)
                val s = tr.translate()
                sb.append(s)
            }

            is PrimitiveType -> {
                val tr = findFor(type)
                val s = tr.translate()
                sb.append(s)
            }

            is ClassOrInterfaceType -> {
                val typeName = type.nameAsString
                if (typeName != "String" || !fileName(parameter).contains("Test"))
                    throw CodeUnsupportedException(parameter)
                sb.append("String")
            }

            else -> throw CodeUnsupportedException(parameter)
        }

        sb.append(' ').append(parameter.nameAsString)

        return sb.toString()
    }
}

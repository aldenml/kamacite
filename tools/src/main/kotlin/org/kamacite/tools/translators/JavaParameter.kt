/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaParameter(
    val parameter: Parameter,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        val type = parameter.type
        when (type) {

            is ArrayType -> Unit

            is PrimitiveType -> Unit

            is ClassOrInterfaceType -> {
                val typeName = type.nameAsString
                if (typeName != "String" || isTestCode(parameter))
                    throw CodeUnsupportedException(parameter)
            }

            else -> throw CodeUnsupportedException(parameter)
        }

        sb.append(declareParameter())

        return sb.toString()
    }

    abstract fun declareParameter(): String
}

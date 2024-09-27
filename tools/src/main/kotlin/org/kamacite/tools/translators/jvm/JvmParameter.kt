/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.type.ClassOrInterfaceType
import org.kamacite.tools.translators.JavaParameter

class JvmParameter(
    parameter: Parameter,
) : JavaParameter(parameter), JvmTranslator {

    override fun declareParameter(): String {
        val sb = StringBuilder()

        val type = parameter.type
        when (type) {

            is ClassOrInterfaceType -> sb.append("String")

            else -> {
                val tr = findFor(type)
                val s = tr.translate()
                sb.append(s)
            }
        }

        sb.append(' ').append(parameter.nameAsString)

        return sb.toString()
    }
}

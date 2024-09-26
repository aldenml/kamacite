/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.body.MethodDeclaration
import org.kamacite.tools.translators.JavaMethodDeclaration

class JvmMethodDeclaration(
    methodDeclaration: MethodDeclaration,
) : JavaMethodDeclaration(methodDeclaration), JvmTranslator {

    override fun beginMethod(): String {
        val sb = StringBuilder()

        val modifiers = methodDeclaration.modifiers.map { it ->
            it.toString().trim()
        }.joinToString(" ")

        val returnType = methodDeclaration.type
        val name = methodDeclaration.nameAsString

        if (name.startsWith("test_"))
            sb.append("@Test").append(newLine())

        sb.append("$modifiers $returnType $name(")

        val args = methodDeclaration.parameters.map { parameter ->
            val tr = findFor(parameter)
            tr.translate()
        }.joinToString(", ")

        sb.append(args)

        sb.append(") {")

        return sb.toString()
    }

    override fun endMethod(): String {
        return "}"
    }
}

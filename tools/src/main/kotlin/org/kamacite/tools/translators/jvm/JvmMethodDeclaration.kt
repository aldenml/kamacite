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

    override fun begin(): String {
        val sb = StringBuilder()

        val modifiers = methodDeclaration.modifiers.map { it ->
            it.toString().trim()
        }.joinToString(" ")

        val returnType = methodDeclaration.type
        val name = methodDeclaration.nameAsString

        if (name.startsWith("test_"))
            sb.append("@Test").append(newLine())

        sb.append("$modifiers $returnType $name() {")

        return sb.toString()
    }

    override fun end(): String {
        return "}"
    }
}

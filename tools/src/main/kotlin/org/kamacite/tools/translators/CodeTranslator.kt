/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.Node
import org.kamacite.tools.CodeUnsupportedException

interface CodeTranslator {

    enum class Target(val prefix: String) {
        JVM("Jvm"),
    }

    val target: Target

    fun translate(): String

    fun findFor(node: Node): CodeTranslator {
        val packageName = "org.kamacite.tools.translators.${target.prefix.lowercase()}"
        val className = target.prefix + node.javaClass.simpleName

        try {
            val clazz = Class.forName("$packageName.$className")
            return clazz.getDeclaredConstructor(
                node.javaClass,
            ).newInstance(
                node,
            ) as CodeTranslator
        } catch (ex: Throwable) {
            throw CodeUnsupportedException(node, ex)
        }
    }

    fun newLine() = System.lineSeparator()

    fun indent(s: String): String {
        val indentWith = 4

        return s.replaceIndent(" ".repeat(indentWith))
    }
}

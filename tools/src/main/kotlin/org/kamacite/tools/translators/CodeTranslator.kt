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
        C("C"),
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

    fun fileName(node: Node): String {
        return node.findCompilationUnit().get()
            .storage.get()
            .fileName
    }

    fun isTestCode(node: Node): Boolean {
        return fileName(node).endsWith("Test.java")
    }

    fun isUtilOrTestCode(node: Node): Boolean {
        return fileName(node).endsWith("Util.java") || isTestCode(node)
    }

    fun newLine() = System.lineSeparator()

    fun indent(s: String): String {
        val indentWith = 4

        return s.prependIndent(" ".repeat(indentWith))
    }

    companion object {

        fun camelToSnakeCase(s: String): String {
            val result = StringBuilder()
            for ((index, c) in s.withIndex()) {
                if (c.isUpperCase()) {
                    if (index != 0) {
                        result.append('_')
                    }
                    result.append(c.lowercaseChar())
                } else {
                    result.append(c)
                }
            }
            return result.toString()
        }
    }
}

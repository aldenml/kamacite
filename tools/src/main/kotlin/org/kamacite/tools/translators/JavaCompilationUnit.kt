/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.CompilationUnit
import org.kamacite.tools.parser.JavaFile

abstract class JavaCompilationUnit(
    val compilationUnit: CompilationUnit,
) : CodeTranslator {

    protected val file = JavaFile(compilationUnit)

    override fun translate(): String {
        val sb = StringBuilder()

        sb.append(beginFile()).append(newLine())

        file.methods().forEach { method ->
            val tr = findFor(method.methodDeclaration)
            val s = tr.translate()
            sb.append(addMethod(s)).append(newLine())
        }

        sb.append(endFile()).append(newLine())

        return sb.toString()
    }

    abstract fun beginFile(): String

    abstract fun endFile(): String

    abstract fun addMethod(s: String): String
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.CompilationUnit
import org.kamacite.tools.translators.JavaCompilationUnit

class JvmCompilationUnit(
    compilationUnit: CompilationUnit,
) : JavaCompilationUnit(compilationUnit), JvmTranslator {

    override fun beginFile(): String {
        val sb = StringBuilder()

        val license = compilationUnit.comment.get()
        sb.append(license).append(newLine())

        val packageName = compilationUnit.packageDeclaration.get().toString()
            .replace(".reference", ".jvm")
            .trim()
        sb.append(packageName)
            .append(newLine())
            .append(newLine())

        val imports = compilationUnit.imports.map {
            it.toString()
                .trim()
                .replace(".reference.", ".jvm.")
        }.joinToString(newLine())
        sb.append(imports)
            .append(newLine())
            .append(newLine())

        val className = fileName(compilationUnit).removeSuffix(".java")
        sb.append("public class $className {").append(newLine())

        return sb.toString()
    }

    override fun endFile(): String {
        return "}"
    }

    override fun addMethod(s: String): String {
        return indent(s)
    }
}

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
        val className = fileName(compilationUnit).removeSuffix(".java")
        return "public class $className {"
    }

    override fun endFile(): String {
        return "}"
    }

    override fun addMethod(s: String): String {
        return indent(s)
    }
}

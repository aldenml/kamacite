/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.c

import com.github.javaparser.ast.CompilationUnit
import org.kamacite.tools.translators.JavaCompilationUnit

class CCompilationUnit(
    compilationUnit: CompilationUnit,
) : JavaCompilationUnit(compilationUnit), CTranslator {

    override fun beginFile(): String {
        return "{"
    }

    override fun endFile(): String {
        return "}"
    }

    override fun addMethod(s: String): String {
        return indent(s)
    }
}

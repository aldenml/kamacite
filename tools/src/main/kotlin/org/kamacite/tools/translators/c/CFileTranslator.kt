/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.c

import org.kamacite.tools.parser.JavaFile
import org.kamacite.tools.translators.CodeTranslator
import org.kamacite.tools.translators.FileTranslator
import java.nio.file.Files
import java.nio.file.Path

class CFileTranslator(
    file: JavaFile,
) : FileTranslator(file) {

    override fun translate() {
        val tr = CCompilationUnit(file.compilationUnit)
        val code = tr.translate()
        val dest = findDest()

        Files.writeString(dest, code)
    }

    private fun findDest(): Path {
        val dir = file.compilationUnit.storage.get().directory.toString()
        val newDir = dir
            .replaceFirst("/reference/", "/translations/c/")
            .replaceFirst("/src/main/java/org/kamacite/reference", "/src")
            .replaceFirst("/src/test/java/org/kamacite/reference", "/test")

        val fileName = file.compilationUnit.storage.get().fileName
        val newFileName = fileName
            .let { CodeTranslator.camelToSnakeCase(it) }
            .let { it -> if (it.endsWith("_test.java")) "test_" + it.replace("_test", "") else it }
            .let { if (fileName == "TestUtil.java") "kmc_test.c" else it }
            .replace(".java", ".c")

        return Path.of(newDir, newFileName)
    }
}

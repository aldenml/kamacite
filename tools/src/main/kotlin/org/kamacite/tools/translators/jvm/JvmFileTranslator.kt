/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import org.kamacite.tools.parser.JavaFile
import org.kamacite.tools.translators.FileTranslator
import java.nio.file.Files
import java.nio.file.Path

class JvmFileTranslator(
    file: JavaFile,
) : FileTranslator(file) {

    override fun translate() {
        val tr = JvmCompilationUnit(file.compilationUnit)
        val code = tr.translate()
        val dest = findDest()

        Files.writeString(dest, code)
    }

    private fun findDest(): Path {
        val path = file.compilationUnit.storage.get().path.toString()
        val newPath = path
            .replaceFirst("/reference/", "/translations/jvm/")
            .replaceFirst("/reference/", "/internal/")
        return Path.of(newPath)
    }
}

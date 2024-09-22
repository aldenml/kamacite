/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.parser

import com.github.javaparser.JavaParser
import org.kamacite.tools.FileParseException
import java.nio.file.Path
import java.nio.file.Paths

class ReferenceParser {

    fun parse(): List<JavaFile> {

        val result = mutableListOf<JavaFile>()

        val files = srcPath()
            .toFile()
            .walkTopDown()
            .toList()
            .filter { it.extension == "java" }


        files.map { file ->

            val javaParser = JavaParser()
            val parseResult = javaParser.parse(file)

            if (parseResult.isSuccessful) {
                val compilationUnit = parseResult.result.get()
                result.add(JavaFile(compilationUnit))
            } else
                throw FileParseException(file, parseResult.problems)
        }

        return result
    }

    private fun srcPath(): Path {
        val workingPath = Paths.get("").toAbsolutePath().toString()

        val toolsSuffix = "tools"

        val rootPath = if (workingPath.endsWith(toolsSuffix))
            workingPath.removeSuffix(toolsSuffix)
        else
            workingPath

        return Paths.get(rootPath, "reference", "src")
    }
}

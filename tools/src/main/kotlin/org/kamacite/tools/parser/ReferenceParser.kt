/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.parser

import com.github.javaparser.JavaParser
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

            if (parseResult.isSuccessful && parseResult.result.isPresent) {
                val compilationUnit = parseResult.result.get()
                result.add(JavaFile(compilationUnit))
            } else {
                val error = parseResult.problems.joinToString { it.message }
                throw IllegalArgumentException(error)
            }
        }

        return result
    }

    private fun srcPath(): Path {
        val workingPath = Paths.get("").toAbsolutePath().toString()

        val rootPath = if (workingPath.endsWith("tools"))
            workingPath.removeSuffix("tools")
        else
            workingPath

        return Paths.get(rootPath, "reference", "src")
    }
}

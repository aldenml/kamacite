/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools

import com.github.javaparser.Problem
import com.github.javaparser.ast.Node
import org.kamacite.tools.parser.JavaFile
import java.io.File

class FileParseException(
    val file: File,
    val problems: List<Problem>,
) : Exception() {

    override val message: String?
        get() {
            val problemsStr = problems.joinToString { it.message }
            return "Parse problems in ${file.name}: $problemsStr"
        }
}

class CodeUnsupportedException(
    val node: Node,
    cause: Throwable?,
) : Exception(cause) {

    constructor(node: Node) : this(node, null)

    override val message: String?
        get() {
            val file = JavaFile(node.findCompilationUnit().get())
            val position = node.range.get().begin
            return "Code unsupported at ${file.name()}, line=${position.line}, column=${position.column}: $node"
        }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ForStmt
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaForStmt(
    val stmt: ForStmt,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        if (stmt.initialization.count() != 1)
            throw CodeUnsupportedException(stmt)
        if (stmt.update.count() != 1)
            throw CodeUnsupportedException(stmt)

        sb.append(beginFor()).append(newLine())

        val body = stmt.body
        val statements = when (body) {

            is BlockStmt -> body.statements

            else -> throw CodeUnsupportedException(stmt)
        }

        statements.forEach { statement ->
            val tr = findFor(statement)
            val s = indent(tr.translate())
            sb.append(s).append(newLine())
        }

        sb.append(endFor()).append(newLine())

        return sb.toString()
    }

    abstract fun beginFor(): String

    abstract fun endFor(): String
}

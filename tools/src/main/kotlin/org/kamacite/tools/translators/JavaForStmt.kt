/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.expr.Expression
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

        val initialization = stmt.initialization[0]
        val compare = stmt.compare.get()
        val update = stmt.update[0]

        validateForExpressions(initialization, compare, update)

        sb.append(beginFor(initialization, compare, update)).append(newLine())

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

    abstract fun beginFor(
        initialization: Expression,
        compare: Expression,
        update: Expression
    ): String

    abstract fun endFor(): String

    private fun validateForExpressions(
        initialization: Expression,
        compare: Expression,
        update: Expression
    ) {

    }
}

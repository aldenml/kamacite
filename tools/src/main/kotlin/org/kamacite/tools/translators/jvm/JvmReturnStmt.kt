/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.stmt.ReturnStmt
import org.kamacite.tools.CodeUnsupportedException

class JvmReturnStmt(
    val stmt: ReturnStmt,
) {

    fun translate(): String {
        val sb = StringBuilder()

        sb.append("return ")

        val expr = stmt.expression.get()
        when (expr) {

            is BinaryExpr -> sb.append(expr)

            else -> throw CodeUnsupportedException(expr)
        }

        return sb.toString()
    }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.stmt.AssertStmt
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.ForStmt
import org.kamacite.tools.CodeUnsupportedException

class JvmBlockStmt(
    val stmt: BlockStmt,
) {

    fun translate(): String {
        val sb = StringBuilder()

        stmt.statements.forEach {
            when (it) {

                is ExpressionStmt -> sb.append(JvmExpressionStmt(it).translate()).append(System.lineSeparator())

                is AssertStmt -> sb.append(JvmAssertStmt(it).translate()).append(System.lineSeparator())

                is ForStmt -> sb.append(JvmForStmt(it).translate()).append(System.lineSeparator())

                else -> throw CodeUnsupportedException(it)
            }
        }

        return sb.toString()
    }
}

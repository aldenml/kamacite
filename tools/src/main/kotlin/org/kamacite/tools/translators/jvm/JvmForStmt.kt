/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ForStmt
import org.kamacite.tools.CodeUnsupportedException

class JvmForStmt(
    val stmt: ForStmt,
) {

    fun translate(): String {
        val sb = StringBuilder()

        sb.append("for(${stmt.initialization}; ${stmt.compare}; ${stmt.update}) {")

        val body = stmt.body
        when (body) {

            is BlockStmt -> sb.append(JvmBlockStmt(body).translate())

            else -> throw CodeUnsupportedException(stmt)
        }

        sb.append('}')

        return sb.toString()
    }
}

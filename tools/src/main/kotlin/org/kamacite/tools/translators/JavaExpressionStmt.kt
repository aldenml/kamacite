/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.stmt.ExpressionStmt

abstract class JavaExpressionStmt(
    val stmt: ExpressionStmt,
) : CodeTranslator {

    override fun translate(): String {
        val expr = stmt.expression
        val r = findFor(expr)
        return r.translate()
    }
}

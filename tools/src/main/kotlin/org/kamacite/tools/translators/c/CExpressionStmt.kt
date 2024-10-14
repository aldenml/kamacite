/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.c

import com.github.javaparser.ast.stmt.ExpressionStmt
import org.kamacite.tools.translators.JavaExpressionStmt

class CExpressionStmt(
    stmt: ExpressionStmt,
) : JavaExpressionStmt(stmt), CTranslator {

    override fun translate(): String {
        return "expression"
    }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.stmt.ExpressionStmt
import org.kamacite.tools.CodeUnsupportedException

class JvmExpressionStmt(
    val stmt: ExpressionStmt,
) {

    fun translate(): String {
        val expr = stmt.expression
        return when (expr) {

            is VariableDeclarationExpr -> JvmVariableDeclarationExpr(expr).translate()

            else -> throw CodeUnsupportedException(expr)
        }
    }
}

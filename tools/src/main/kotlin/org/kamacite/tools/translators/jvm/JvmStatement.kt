/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.stmt.AssertStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.ForStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.github.javaparser.ast.stmt.Statement
import org.kamacite.tools.CodeUnsupportedException

class JvmStatement(
    val stmt: Statement,
) {

    fun translate(): String = when (stmt) {

        is ExpressionStmt -> JvmExpressionStmt(stmt).translate()

        is ForStmt -> JvmForStmt(stmt).translate()

        is AssertStmt -> JvmAssertStmt(stmt).translate()

        is ReturnStmt -> JvmReturnStmt(stmt).translate()

        else -> throw CodeUnsupportedException(stmt)
    }
}

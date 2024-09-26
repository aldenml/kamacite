/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.ForStmt
import org.kamacite.tools.translators.JavaForStmt

class JvmForStmt(
    stmt: ForStmt,
) : JavaForStmt(stmt), JvmTranslator {

    override fun beginFor(
        initialization: Expression,
        compare: Expression,
        update: Expression,
    ): String {
        return "for(${initialization}; ${compare}; ${update}) {"
    }

    override fun endFor(): String {
        return "}"
    }
}

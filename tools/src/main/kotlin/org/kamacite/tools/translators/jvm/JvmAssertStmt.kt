/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.stmt.AssertStmt
import org.kamacite.tools.translators.JavaAssertStmt

class JvmAssertStmt(
    stmt: AssertStmt,
) : JavaAssertStmt(stmt), JvmTranslator {

    override fun translate(): String = stmt.toString()
}

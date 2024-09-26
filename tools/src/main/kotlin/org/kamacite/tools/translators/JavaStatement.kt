/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.stmt.Statement

abstract class JavaStatement(
    val stmt: Statement,
) : CodeTranslator {

    override fun translate(): String = findFor(stmt).translate()
}

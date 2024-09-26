/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.stmt.AssertStmt

abstract class JavaAssertStmt(
    val stmt: AssertStmt,
) : CodeTranslator

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.stmt.Statement
import org.kamacite.tools.translators.JavaStatement

class JvmStatement(
    stmt: Statement,
) : JavaStatement(stmt), JvmTranslator

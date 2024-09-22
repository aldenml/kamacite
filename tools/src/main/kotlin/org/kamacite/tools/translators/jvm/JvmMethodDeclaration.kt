/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.body.MethodDeclaration

class JvmMethodDeclaration(
    val methodDeclaration: MethodDeclaration,
) {

    fun translate() {
        val statements = methodDeclaration.body.get().statements

        statements.forEach { statement ->
            val r = JvmStatement(statement).translate()
            println(r)
        }
    }
}

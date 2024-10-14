/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.c

import com.github.javaparser.ast.body.MethodDeclaration
import org.kamacite.tools.translators.JavaMethodDeclaration

class CMethodDeclaration(
    methodDeclaration: MethodDeclaration,
) : JavaMethodDeclaration(methodDeclaration), CTranslator {

    override fun translate(): String {
        return "method"
    }

    override fun beginMethod(): String {
        return "method {"
    }

    override fun endMethod(): String {
        return "}"
    }
}

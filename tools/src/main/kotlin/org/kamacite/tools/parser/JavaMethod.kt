/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.parser

import com.github.javaparser.ast.body.MethodDeclaration

class JavaMethod(
    val methodDeclaration: MethodDeclaration,
) {

    fun name(): String {
        return methodDeclaration.nameAsString
    }
}

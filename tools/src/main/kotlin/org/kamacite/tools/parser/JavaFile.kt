/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.parser

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.visitor.VoidVisitorAdapter

class JavaFile(
    val compilationUnit: CompilationUnit,
) {

    fun name(): String {
        return compilationUnit.storage.get().fileName
    }

    fun methods(): List<JavaMethod> {
        val result = mutableListOf<JavaMethod>()

        compilationUnit.accept(object : VoidVisitorAdapter<Void>() {
            override fun visit(n: MethodDeclaration, arg: Void?) {
                super.visit(n, arg)
                result.add(JavaMethod(n))
            }
        }, null)

        return result
    }
}

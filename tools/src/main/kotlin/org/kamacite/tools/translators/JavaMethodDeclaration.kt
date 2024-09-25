/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.CodeUnsupportedException

abstract class JavaMethodDeclaration(
    val methodDeclaration: MethodDeclaration,
) : CodeTranslator {

    override fun translate(): String {
        val sb = StringBuilder()

        validateMethodDeclaration()

        sb.append(begin()).append(newLine())

        val statements = methodDeclaration.body.get().statements

        statements.forEach { statement ->
            val r = findFor(statement)
            val s = indent(r.translate())
            sb.append(s).append(newLine())
        }

        sb.append(end()).append(newLine())

        return sb.toString()
    }

    abstract fun begin(): String

    abstract fun end(): String

    private fun validateMethodDeclaration() {
        if (!methodDeclaration.isPublic)
            throw CodeUnsupportedException(methodDeclaration)

        val isTest = methodDeclaration.nameAsString.startsWith("test_")
        if (!(isTest || methodDeclaration.isStatic))
            throw CodeUnsupportedException(methodDeclaration)

        val returnType = methodDeclaration.type
        if (!returnType.isVoidType && returnType != PrimitiveType.intType())
            throw CodeUnsupportedException(methodDeclaration)

        if (methodDeclaration.thrownExceptions.count() > 0)
            throw CodeUnsupportedException(methodDeclaration)
    }
}

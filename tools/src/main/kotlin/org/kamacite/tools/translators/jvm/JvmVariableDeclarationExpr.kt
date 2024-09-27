/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.ArrayCreationExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import org.kamacite.tools.translators.JavaVariableDeclarationExpr

class JvmVariableDeclarationExpr(
    expr: VariableDeclarationExpr,
) : JavaVariableDeclarationExpr(expr), JvmTranslator {

    override fun declareVariable(): String {
        val sb = StringBuilder()

        val variable = expr.variables[0]
        val initializer = variable.initializer.get()

        val type = expr.commonType
        val tr = findFor(type)
        val s = tr.translate()
        sb.append(s).append(' ')

        val name = variable.nameAsString
        sb.append(name)

        sb.append(" = ")

        when (initializer) {

            is ArrayCreationExpr -> {
                val tr = findFor(initializer)
                val s = tr.translate()
                sb.append(s)
            }

            is MethodCallExpr -> {
                val tr = findFor(initializer)
                val s = tr.translate().removeSuffix(";")
                sb.append(s)
            }

            else -> sb.append(initializer.toString())
        }

        sb.append(';')

        return sb.toString()
    }
}

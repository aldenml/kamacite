/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.type.ArrayType
import com.github.javaparser.ast.type.PrimitiveType
import org.kamacite.tools.translators.JavaVariableDeclarationExpr

class JvmVariableDeclarationExpr(
    expr: VariableDeclarationExpr,
) : JavaVariableDeclarationExpr(expr), JvmTranslator {

    override fun declareArrayVariable(type: ArrayType, name: String): String {
        val tr = findFor(type)
        val tp = tr.translate()
        return "$tp $name"
    }

    override fun declareVariable(type: PrimitiveType, name: String): String {
        val tr = findFor(type)
        val tp = tr.translate()
        return "$tp $name"
    }
}

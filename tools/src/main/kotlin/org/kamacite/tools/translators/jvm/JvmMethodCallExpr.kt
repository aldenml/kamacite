/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import com.github.javaparser.ast.expr.MethodCallExpr
import org.kamacite.tools.translators.JavaMethodCallExpr

class JvmMethodCallExpr(
    methodCall: MethodCallExpr
) : JavaMethodCallExpr(methodCall), JvmTranslator {

    override fun invokeMethod(): String {
        val sb = StringBuilder()

        sb.append(methodCall.nameAsString).append('(')

        val args = methodCall.arguments.joinToString { arg ->
            arg.toString()
        }

        sb.append(args).append(");")

        return sb.toString()
    }
}

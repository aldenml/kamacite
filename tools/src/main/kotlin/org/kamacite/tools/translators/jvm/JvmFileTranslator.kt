/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import org.kamacite.tools.parser.JavaFile
import org.kamacite.tools.translators.FileTranslator

class JvmFileTranslator(file: JavaFile) : FileTranslator(file) {

    override fun translate() {
        file.methods().forEach { method ->
            val r = JvmMethodDeclaration(method.methodDeclaration).translate()
            println(r)
        }
    }
}

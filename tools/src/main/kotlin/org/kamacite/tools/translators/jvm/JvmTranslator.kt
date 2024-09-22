/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import org.kamacite.tools.parser.JavaFile
import org.kamacite.tools.translators.FileTranslator

class JvmTranslator(file: JavaFile) : FileTranslator(file) {

    override fun translate() {
        file.methods().forEach { method ->
            JvmMethodDeclaration(method.methodDeclaration).translate()
        }
    }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators

import org.kamacite.tools.parser.JavaFile

abstract class FileTranslator(
    val file: JavaFile,
) {

    abstract fun translate()
}

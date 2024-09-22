/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools

import org.kamacite.tools.parser.ReferenceParser
import org.kamacite.tools.translators.jvm.JvmTranslator

fun main() {

    val parser = ReferenceParser()

    parser.parse().forEach { file ->
        JvmTranslator(file).translate()
    }
}

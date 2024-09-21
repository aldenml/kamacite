/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools

import org.kamacite.tools.parser.ReferenceParser

fun main() {

    val parser = ReferenceParser()

    parser.parse().forEach { file ->

        file.methods().forEach { method ->

            println("${file.name()}:${method.name()}")
        }
    }
}

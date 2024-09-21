/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.parser

import kotlin.test.Test
import kotlin.test.assertEquals

class ReferenceParserTest {

    @Test
    fun testParseFiles() {

        val parser = ReferenceParser()
        val files = parser.parse()

        assertEquals(3, files.count())
    }
}

/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.tools.translators.jvm

import org.kamacite.tools.translators.CodeTranslator

interface JvmTranslator : CodeTranslator {

    override val target: CodeTranslator.Target
        get() = CodeTranslator.Target.JVM
}

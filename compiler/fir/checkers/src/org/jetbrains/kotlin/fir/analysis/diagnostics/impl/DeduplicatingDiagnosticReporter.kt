/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.diagnostics.impl

import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.diagnostics.AbstractFirDiagnosticFactory
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnostic

class DeduplicatingDiagnosticReporter(private val inner: DiagnosticReporter) : DiagnosticReporter() {

    private val reported = mutableSetOf<Pair<FirSourceElement, AbstractFirDiagnosticFactory<*, *>>>()

    override fun report(diagnostic: FirDiagnostic<*>?, context: CheckerContext) {
        if (diagnostic != null && reported.add(Pair(diagnostic.element, diagnostic.factory))) {
            inner.report(diagnostic, context)
        }
    }
}

fun DiagnosticReporter.deduplicating(): DeduplicatingDiagnosticReporter = DeduplicatingDiagnosticReporter(this)
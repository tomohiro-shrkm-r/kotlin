/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.extended

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fir.FirFakeSourceElementKind
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.implicitModality
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirDeclarationSyntaxChecker
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors.REDUNDANT_MODALITY_MODIFIER
import org.jetbrains.kotlin.fir.analysis.diagnostics.modalityModifier
import org.jetbrains.kotlin.fir.analysis.diagnostics.reportOn
import org.jetbrains.kotlin.fir.declarations.FirClass
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirStatusOwner
import org.jetbrains.kotlin.fir.declarations.utils.modality
import org.jetbrains.kotlin.psi.KtDeclaration

object RedundantModalityModifierSyntaxChecker : FirDeclarationSyntaxChecker<FirDeclaration, KtDeclaration>() {

    override fun isApplicable(element: FirDeclaration, source: FirSourceElement): Boolean =
        source.kind !is FirFakeSourceElementKind && element is FirStatusOwner

    override fun checkLightTree(
        element: FirDeclaration,
        source: FirSourceElement,
        context: CheckerContext,
        reporter: DiagnosticReporter
    ) {
        require(element is FirStatusOwner)
        val modality = element.modality ?: return
        if (
            modality == Modality.FINAL
            && (context.containingDeclarations.last() as? FirClass)?.classKind == ClassKind.INTERFACE
        ) return

        if (source.treeStructure.modalityModifier(source.lighterASTNode) == null) return
        val implicitModality = element.implicitModality(context)
        if (modality == implicitModality) {
            reporter.reportOn(source, REDUNDANT_MODALITY_MODIFIER, context)
        }
    }
}

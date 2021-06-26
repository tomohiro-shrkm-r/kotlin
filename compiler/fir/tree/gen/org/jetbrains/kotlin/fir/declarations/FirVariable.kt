/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirModuleData
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirStatement
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

sealed class FirVariable : FirCallableMemberDeclaration(), FirStatement {
    abstract override val source: FirSourceElement?
    abstract override val moduleData: FirModuleData
    abstract override val resolvePhase: FirResolvePhase
    abstract override val origin: FirDeclarationOrigin
    abstract override val attributes: FirDeclarationAttributes
    abstract override val returnTypeRef: FirTypeRef
    abstract override val receiverTypeRef: FirTypeRef?
    abstract override val typeParameters: List<FirTypeParameterRef>
    abstract override val status: FirDeclarationStatus
    abstract override val containerSource: DeserializedContainerSource?
    abstract override val dispatchReceiverType: ConeKotlinType?
    abstract val name: Name
    abstract override val symbol: FirVariableSymbol<out FirVariable>
    abstract val initializer: FirExpression?
    abstract val delegate: FirExpression?
    abstract val isVar: Boolean
    abstract val isVal: Boolean
    abstract val getter: FirPropertyAccessor?
    abstract val setter: FirPropertyAccessor?
    abstract override val annotations: List<FirAnnotationCall>

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R = visitor.visitVariable(this, data)

    @Suppress("UNCHECKED_CAST")
    override fun <E: FirElement, D> transform(transformer: FirTransformer<D>, data: D): E = 
        transformer.transformVariable(this, data) as E

    abstract override fun replaceResolvePhase(newResolvePhase: FirResolvePhase)

    abstract override fun replaceReturnTypeRef(newReturnTypeRef: FirTypeRef)

    abstract override fun replaceReceiverTypeRef(newReceiverTypeRef: FirTypeRef?)

    abstract fun replaceInitializer(newInitializer: FirExpression?)

    abstract override fun <D> transformReturnTypeRef(transformer: FirTransformer<D>, data: D): FirVariable

    abstract override fun <D> transformReceiverTypeRef(transformer: FirTransformer<D>, data: D): FirVariable

    abstract override fun <D> transformTypeParameters(transformer: FirTransformer<D>, data: D): FirVariable

    abstract override fun <D> transformStatus(transformer: FirTransformer<D>, data: D): FirVariable

    abstract fun <D> transformInitializer(transformer: FirTransformer<D>, data: D): FirVariable

    abstract fun <D> transformDelegate(transformer: FirTransformer<D>, data: D): FirVariable

    abstract fun <D> transformGetter(transformer: FirTransformer<D>, data: D): FirVariable

    abstract fun <D> transformSetter(transformer: FirTransformer<D>, data: D): FirVariable

    abstract override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirVariable

    abstract fun <D> transformOtherChildren(transformer: FirTransformer<D>, data: D): FirVariable
}

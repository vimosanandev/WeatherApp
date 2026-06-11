package com.vimosanan.weatherapp.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out Result>(
    protected val dispatcher: CoroutineDispatcher,
) : UseCase {
    operator fun invoke(params: Params): Flow<Result> =
        execute(params).flowOn(dispatcher)

    protected abstract fun execute(params: Params): Flow<Result>
}

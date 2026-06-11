package com.vimosanan.weatherapp.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, out Result>(
    protected val dispatcher: CoroutineDispatcher,
) : UseCase {
    suspend operator fun invoke(params: Params): Result =
        withContext(dispatcher) {
            execute(params)
        }

    protected abstract suspend fun execute(params: Params): Result
}

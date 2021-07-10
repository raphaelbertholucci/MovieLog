package com.bertholucci.data.helpers

sealed class Response<out V> {
    data class Success<out V>(val value: V) : Response<V>()
    data class Loading(val loading: Boolean) : Response<Nothing>()
    data class Failure(val error: Throwable) : Response<Nothing>()

    private fun <V> success(value: V): Response<V> = Success(value)
    private fun loading(loading: Boolean): Response<Nothing> = Loading(loading)
    private fun failure(value: Throwable): Response<Nothing> = Failure(value)

    fun <V> response(action: () -> V): Response<V> =
        try {
            success(action())
        } catch (e: Exception) {
            failure(e)
        }
}

inline fun <V, A> Response<V>.fold(
    loading: (Boolean) -> A,
    error: (Throwable) -> A,
    success: (V) -> A
): A = when (this) {
    is Response.Failure -> error(this.error)
    is Response.Success -> success(this.value)
    is Response.Loading -> loading(this.loading)
}
package com.example.tave.common.util.state

sealed class InitPasswordState {
    object Idle: InitPasswordState()
    object IsLoading: InitPasswordState()
    data class IsComplete(val result: Result<Unit>): InitPasswordState()
    data class IsFailed(val result: Result<Unit>): InitPasswordState()
}

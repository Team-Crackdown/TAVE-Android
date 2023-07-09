package com.example.tave.common.util.state

sealed class LogInUserState {
    object Idle: LogInUserState()
    object IsLoading: LogInUserState()
    data class IsSuccess(val result: Result<Unit>): LogInUserState()
    data class IsFailed(val result: Result<Unit>): LogInUserState()
    data class IsSMSCheckNeeded(val result: Result<Unit>): LogInUserState()
}

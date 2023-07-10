package com.example.tave.common.util.state

sealed class SendSMSCodeState {
    object Idle: SendSMSCodeState()
    object IsLoading: SendSMSCodeState()
    data class IsComplete(val result: Result<Unit>): SendSMSCodeState()
    data class IsFailed(val result: Result<Unit>): SendSMSCodeState()
}

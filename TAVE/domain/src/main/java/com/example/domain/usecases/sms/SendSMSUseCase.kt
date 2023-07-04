package com.example.domain.usecases.sms

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendSMSUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        phoneNumber: String
    ): Flow<Result<Unit>> = taveAPIRepository.sendSMSCode(phoneNumber)
}
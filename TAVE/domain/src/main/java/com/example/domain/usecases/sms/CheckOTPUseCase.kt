package com.example.domain.usecases.sms

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckOTPUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        phoneNumber: String,
        otpCode: String
    ): Flow<Result<Unit>> = taveAPIRepository.checkOTPCode(accessToken, phoneNumber, otpCode)
}
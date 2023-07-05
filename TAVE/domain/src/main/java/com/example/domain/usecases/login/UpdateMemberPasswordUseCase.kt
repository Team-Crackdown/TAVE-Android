package com.example.domain.usecases.login

import com.example.domain.entity.login.PasswordModifyEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateMemberPasswordUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        passwordModifyEntity: PasswordModifyEntity
    ): Flow<Result<Unit>> = taveAPIRepository.updateMemberPassword(accessToken, passwordModifyEntity)
}
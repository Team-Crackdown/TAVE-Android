package com.example.domain.usecases.login

import com.example.domain.entity.login.ModifyPasswordEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModifyPasswordUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        passwordModifyEntity: ModifyPasswordEntity
    ): Flow<Result<Unit>> = taveAPIRepository.updateMemberPassword(accessToken, passwordModifyEntity)
}
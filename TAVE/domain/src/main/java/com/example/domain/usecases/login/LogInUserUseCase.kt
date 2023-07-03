package com.example.domain.usecases.login

import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.repository.TaveAPIRepository
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(
  private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        logInBodyEntity: LogInBodyEntity
    ) = taveAPIRepository.userLogIn(logInBodyEntity)
}
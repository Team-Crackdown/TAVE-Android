package com.example.domain.usecases.profile

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCheckedSMSUseCase @Inject constructor(
  private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(accessToken: String): Flow<Boolean> =
        taveAPIRepository.getProfileInfo(accessToken).map { it?.isCheckSMS!! }
}
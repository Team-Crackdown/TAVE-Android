package com.example.domain.usecases.profile

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTeamIdUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(accessToken: String): Flow<Int> =
        taveAPIRepository.getProfileInfo(accessToken).map { it?.userTeamID!! }
}
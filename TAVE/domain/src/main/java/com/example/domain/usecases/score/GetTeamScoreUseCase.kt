package com.example.domain.usecases.score

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamScoreUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        teamID: Int
    ): Flow<Int> = taveAPIRepository.getTeamScore(accessToken, teamID)
}
package com.example.domain.usecases.score

import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamScoreUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        teamID: Int
    ): Flow<TeamScoreEntity?> = taveAPIRepository.getTeamScore(accessToken, teamID)
}
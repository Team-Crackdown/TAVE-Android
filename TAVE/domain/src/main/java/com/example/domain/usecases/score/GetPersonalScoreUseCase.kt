package com.example.domain.usecases.score

import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonalScoreUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        memberId: Int
    ): Flow<UserScoreEntity?> = taveAPIRepository.getPersonalScore(memberId)
}
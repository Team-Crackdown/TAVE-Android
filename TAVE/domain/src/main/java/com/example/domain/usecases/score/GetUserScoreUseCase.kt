package com.example.domain.usecases.score

import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserScoreUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        userUID: Int
    ): Flow<UserScoreEntity?> = taveAPIRepository.getUserScore(userUID)
}
package com.example.domain.usecases.score

import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonalScoreUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        memberId: Int
    ): Flow<Int> = taveAPIRepository.getPersonalScore(accessToken, memberId)
}
package com.example.domain.usecases.profile

import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        userUID: Int
    ): Flow<UserProfileEntity?> = taveAPIRepository.getUserProfile(userUID)
}
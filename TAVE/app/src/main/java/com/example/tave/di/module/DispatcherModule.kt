package com.example.tave.di.module

import com.example.tave.di.qualifier.DefaultDispatcher
import com.example.tave.di.qualifier.IoDispatcher
import com.example.tave.di.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Dispatcher Module
 *  - 코루틴 디스패처 모듈입니다.
 *  - Main Dispatcher : UI에 주로 사용하는 Dispatcher 입니다.
 *  - IO Dispatcher : Network 나 Database CRUD 작업에 주로 사용합니다.
 *  - Default Dispatcher : 위 두 디스패처가 감당하기 힘든 연산 작업을 주로 담당합니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @MainDispatcher @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IoDispatcher @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
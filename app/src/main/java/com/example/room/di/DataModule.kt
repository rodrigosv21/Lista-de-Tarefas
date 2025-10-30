package com.example.room.di

import android.content.Context
import com.example.room.repository.TaskDAO
import com.example.room.repository.TaskDataBase
import com.example.room.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDataBase =
        TaskDataBase.getDatabase(context)

    @Provides
    fun provideTaskDao(database: TaskDataBase): TaskDAO = database.taskDAO()

    @Provides
    @Singleton
    fun provideTaskRepository(database: TaskDataBase): TaskRepository =
        TaskRepository(database.taskDAO())
}

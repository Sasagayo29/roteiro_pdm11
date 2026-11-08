package com.example.pdm_roteiro10.di

import android.content.Context
import androidx.room.Room
import com.example.pdm_roteiro10.data.AppDatabase
import com.example.pdm_roteiro10.data.GroupDao
import com.example.pdm_roteiro10.data.GroupRepositoryImpl
import com.example.pdm_roteiro10.data.VehicleDao
import com.example.pdm_roteiro10.data.VehicleRepositoryImpl
import com.example.pdm_roteiro10.domain.GroupRepository
import com.example.pdm_roteiro10.domain.VehicleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideVehicleDao(db: AppDatabase): VehicleDao = db.vehicleDao()

    @Provides
    fun provideGroupDao(db: AppDatabase): GroupDao = db.groupDao()

    @Provides
    @Singleton
    fun provideVehicleRepository(dao: VehicleDao): VehicleRepository {
        return VehicleRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGroupRepository(dao: GroupDao): GroupRepository {
        return GroupRepositoryImpl(dao)
    }
}
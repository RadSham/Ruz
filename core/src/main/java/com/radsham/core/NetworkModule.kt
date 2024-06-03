package com.radsham.core

import com.radsham.network.FirebaseDatasource
import com.radsham.network.FirebaseDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindFirebaseDatasource(
        firebaseDatasourceImpl:
        FirebaseDatasourceImpl
    ): FirebaseDatasource
}
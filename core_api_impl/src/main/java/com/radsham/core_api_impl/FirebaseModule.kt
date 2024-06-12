package com.radsham.core_api_impl

import com.radsham.core_api.FirebaseDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    abstract fun bindFirebaseDatasource(
        firebaseDatasourceImpl: FirebaseDatasourceImpl
    ): FirebaseDatasource
}
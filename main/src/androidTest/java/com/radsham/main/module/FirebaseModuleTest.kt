package com.radsham.main.module

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api_impl.FirebaseModule
import com.radsham.main.FirebaseDatasourceImplTest
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(components = [SingletonComponent::class],
    replaces = [FirebaseModule::class])
@Module
abstract class FirebaseModuleTest {

    @Binds
    abstract fun bindFirebaseDatasource(
        firebaseDatasourceImplTest: FirebaseDatasourceImplTest
    ): FirebaseDatasource
}
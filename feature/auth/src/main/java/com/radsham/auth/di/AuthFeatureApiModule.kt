package com.radsham.auth.di

import com.radsham.auth.AuthFeatureApiImpl
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.AUTH   )
    abstract fun bindModuleApi(impl: AuthFeatureApiImpl): FeatureApi
}
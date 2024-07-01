package com.radsham.signin.di

import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.signin.SignInFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
abstract class SignInFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.SIGN_IN   )
    abstract fun bindModuleApi(impl: SignInFeatureApiImpl): FeatureApi
}
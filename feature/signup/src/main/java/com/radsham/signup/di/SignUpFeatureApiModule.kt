package com.radsham.signup.di

import com.radsham.signup.SignUpFeatureApiImpl
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
abstract class SignUpFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.SIGN_UP_SCREEN)
    abstract fun bindModuleApi(impl: SignUpFeatureApiImpl): FeatureApi
}
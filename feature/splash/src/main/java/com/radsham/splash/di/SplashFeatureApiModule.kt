package com.radsham.splash.di

import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.splash.SplashFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
abstract class NewEventFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.SPLASH_SCREEN)
    abstract fun bindModuleApi(impl: SplashFeatureApiImpl): FeatureApi
}
package com.radsham.newevent.di

import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.newevent.NewEventFeatureApiImpl
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
    @StringKey(NavScreen.NEW_EVENT_SCREEN)
    abstract fun bindModuleApi(impl: NewEventFeatureApiImpl): FeatureApi
}
package com.radsham.eventdetails.di

import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.eventdetails.EventDetailsFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
abstract class EventDetailsFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.EVENT_DETAILS_SCREEN)
    abstract fun bindModuleApi(impl: EventDetailsFeatureApiImpl): FeatureApi
}
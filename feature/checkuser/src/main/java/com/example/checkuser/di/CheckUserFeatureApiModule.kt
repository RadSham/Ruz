package com.example.checkuser.di

import com.example.checkuser.CheckUserFeatureApiImpl
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
abstract class CheckUserFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.CHECKUSER)
    abstract fun bindModuleApi(impl: CheckUserFeatureApiImpl): FeatureApi
}
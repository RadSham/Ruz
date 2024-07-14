package com.radsham.iamin.di

import com.radsham.core_api.FeatureApi
import com.radsham.core_api.NavScreen
import com.radsham.iamin.IamInFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
abstract class IamInFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.IAMIN_SCREEN)
    abstract fun bindModuleApi(impl: IamInFeatureApiImpl): FeatureApi
}
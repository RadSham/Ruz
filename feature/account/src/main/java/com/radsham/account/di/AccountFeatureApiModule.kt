package com.radsham.account.di

import com.radsham.account.AccountFeatureApiImpl
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
abstract class AccountFeatureApiModule {

    @Binds
    @IntoMap
    @StringKey(NavScreen.ACCOUNT_SCREEN)
    abstract fun bindModuleApi(impl: AccountFeatureApiImpl): FeatureApi
}
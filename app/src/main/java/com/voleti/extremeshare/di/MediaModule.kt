package com.voleti.extremeshare.di

import android.app.Application
import android.content.ContentResolver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object MediaModule {

    @Provides
    @Singleton
    fun provideContextResolver(application: Application):ContentResolver{
       return  application.contentResolver
    }

}
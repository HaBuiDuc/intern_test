package com.example.currencyconverter.di

import android.app.Application
import android.content.Context
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.data.data_sources.CurrencyConvertApi
import com.example.currencyconverter.data.repositories.CurrencyRepositoryImpl
import com.example.currencyconverter.domain.repositories.CurrencyRepository
import com.example.currencyconverter.presentation.viewmodel.CurrencyConvertViewModel
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyConvertApi, context: Context): CurrencyRepository {
        return CurrencyRepositoryImpl(api, context)
    }

    @Provides
    @Singleton
    fun provideCurrencyViewModel(currencyRepository: CurrencyRepository): CurrencyViewModel {
        return CurrencyViewModel(currencyRepository)
    }

    @Provides
    @Singleton
    fun provideCurrencyConvertViewModel(currencyViewModel: CurrencyViewModel): CurrencyConvertViewModel {
        return CurrencyConvertViewModel(currencyViewModel = currencyViewModel)
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY

            }
        ).build()

    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyConvertApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CurrencyConvertApi::class.java)
    }

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}
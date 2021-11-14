package com.clover.mobileapp.di

import com.clover.mobileapp.network.APIInterface
import com.clover.mobileapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// Module class to provide dependency
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Provides dependency of Retrofit
     * @return retrofit reference
     * */
    @Singleton
    @Provides
    fun provideRetrofitService(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    /**
     * Provides dependency of APIInterface
     * @param retrofit is the Retrofit
     * */
    @Singleton
    @Provides
    fun getAPIInterface(retrofit: Retrofit): APIInterface =
        retrofit.create(APIInterface::class.java)

}
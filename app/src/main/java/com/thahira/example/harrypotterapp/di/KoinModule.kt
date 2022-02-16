package com.thahira.example.harrypotterapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thahira.example.harrypotterapp.adapter.HPRecyclerViewAdapter
import com.thahira.example.harrypotterapp.rest.HPRepository
import com.thahira.example.harrypotterapp.rest.HPRepositoryImpl
import com.thahira.example.harrypotterapp.rest.HarryPotterApi
import com.thahira.example.harrypotterapp.view.DetailFragment
import com.thahira.example.harrypotterapp.view.FirstFragment
import com.thahira.example.harrypotterapp.view.StudentFragment
import com.thahira.example.harrypotterapp.viewmodel.HPViewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.reflect.Array.get
import java.util.concurrent.TimeUnit
/*
val repositoryModule = module{
    single{HPRepositoryImpl(get())}
}
 */

val networkModule= module{

    fun provideRepos(harryPotterApi: HarryPotterApi): HPRepository = HPRepositoryImpl(harryPotterApi)

    fun provideMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    fun provideNetworkApi(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(HarryPotterApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(HarryPotterApi::class.java)

    single{ provideRepos(get())}
    single{ provideMoshi() }
    single{ provideLoggingInterceptor() }
    single{ provideOkHttpClient(get()) }
    single{ provideNetworkApi(get(),get()) }
}

 val appModule= module{
    single{ HPRecyclerViewAdapter(get(),get()) }
    single{ FirstFragment() }
     single{ DetailFragment() }
     single { StudentFragment() }
}

val viewModelModule = module{
        viewModel{
            HPViewModel(get())
        }
}
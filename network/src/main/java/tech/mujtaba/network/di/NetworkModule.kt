package tech.mujtaba.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.network.repository.UserRepositoryImpl
import tech.mujtaba.network.service.NetworkService
import javax.inject.Singleton

@Module(includes = [NetworkModule.NetworkBindingModule::class])
class NetworkModule {
    companion object {
        private const val API_BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideNetworkApi(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Module
    internal abstract class NetworkBindingModule {
        @Binds
        internal abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
    }
}
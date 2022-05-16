package pro.fateeva.chuchasgithub.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.fateeva.chuchasgithub.data.UserRepositoryImpl
import pro.fateeva.chuchasgithub.data.usecase.UserListUseCaseImpl
import pro.fateeva.chuchasgithub.data.web.GitHubApi
import pro.fateeva.chuchasgithub.domain.UserRepository
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppDependenciesModule {
    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(api: GitHubApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideUserListUseCase(userRepository: UserRepository): UserListUseCase {
        return UserListUseCaseImpl(userRepository)
    }

    @Provides
    @Named("api_url")
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("api_url") baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }
}
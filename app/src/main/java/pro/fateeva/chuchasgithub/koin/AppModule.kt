package pro.fateeva.chuchasgithub

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.fateeva.chuchasgithub.data.UserRepositoryImpl
import pro.fateeva.chuchasgithub.data.usecase.UserListUseCaseImpl
import pro.fateeva.chuchasgithub.data.web.GitHubApi
import pro.fateeva.chuchasgithub.domain.UserRepository
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase
import pro.fateeva.chuchasgithub.ui.userList.UserListViewModel
import pro.fateeva.chuchasgithub.ui.userProfile.UserProfileViewModel
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

val appModule = module {

    single<Retrofit> {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(get())
            .build()
    }

    single<GitHubApi> {
        get<Retrofit>().create(GitHubApi::class.java)
    }

    factory<Converter.Factory> { GsonConverterFactory.create(GsonBuilder().setLenient().create()) }

    single<UserRepository>{ UserRepositoryImpl(get()) }

    single<UserListUseCase> { UserListUseCaseImpl(get()) }
}

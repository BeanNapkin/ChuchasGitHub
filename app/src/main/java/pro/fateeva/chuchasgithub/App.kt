package pro.fateeva.chuchasgithub

import android.app.Application
import android.content.Context
import pro.fateeva.chuchasgithub.data.web.RetrofitBuilder
import pro.fateeva.chuchasgithub.domain.UserRepository
import pro.fateeva.chuchasgithub.data.UserRepositoryImpl
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase
import pro.fateeva.chuchasgithub.data.usecase.UserListUseCaseImpl

class App : Application() {
    private val gitHubApi = RetrofitBuilder().getGitHubApi()
    private val userRepository: UserRepository by lazy { UserRepositoryImpl(gitHubApi) }

    val userListUseCase: UserListUseCase by lazy  {
        UserListUseCaseImpl(userRepository)
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
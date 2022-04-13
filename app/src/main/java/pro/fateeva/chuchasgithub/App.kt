package pro.fateeva.chuchasgithub

import android.app.Application
import android.content.Context
import pro.fateeva.chuchasgithub.data.UserRepository
import pro.fateeva.chuchasgithub.data.UserRepositoryImpl
import pro.fateeva.chuchasgithub.domain.UserListUseCase
import pro.fateeva.chuchasgithub.domain.UserListUseCaseImpl

class App : Application() {
    private val userRepository: UserRepository by lazy { UserRepositoryImpl() }

    val userListUseCase: UserListUseCase by lazy  {
        UserListUseCaseImpl(userRepository)
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
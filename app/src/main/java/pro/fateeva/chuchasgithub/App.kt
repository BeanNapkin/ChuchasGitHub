package pro.fateeva.chuchasgithub

import android.app.Application
import android.content.Context
import pro.fateeva.chuchasgithub.data.UserRepository
import pro.fateeva.chuchasgithub.data.UserRepositoryImpl
import pro.fateeva.chuchasgithub.domain.UserListUseCase
import pro.fateeva.chuchasgithub.domain.UserListUseCaseImpl
import pro.fateeva.chuchasgithub.domain.UserProfileUseCase
import pro.fateeva.chuchasgithub.domain.UserProfileUseCaseImpl

class App : Application() {
    private val userRepository: UserRepository by lazy { UserRepositoryImpl() }

    val userListUseCase: UserListUseCase by lazy  {
        UserListUseCaseImpl(userRepository)
    }

    val userProfileUseCase: UserProfileUseCase by lazy  {
        UserProfileUseCaseImpl(userRepository)
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
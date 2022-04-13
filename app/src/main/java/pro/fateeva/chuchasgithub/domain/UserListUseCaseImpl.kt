package pro.fateeva.chuchasgithub.domain

import pro.fateeva.chuchasgithub.data.UserRepository
import pro.fateeva.chuchasgithub.domain.entities.User

class UserListUseCaseImpl(private val repository: UserRepository) : UserListUseCase {

    override fun chooseUser(user: User) {
        TODO("Not yet implemented")
    }
}
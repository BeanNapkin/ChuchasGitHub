package pro.fateeva.chuchasgithub.domain

import pro.fateeva.chuchasgithub.data.UserRepository
import pro.fateeva.chuchasgithub.domain.entities.User

class UserListUseCaseImpl(private val repository: UserRepository) : UserListUseCase {

    override fun getUser(position: Int) : User {
        return repository.getUser(position)
    }

    override fun getUserList(): List<User> {
        return repository.getUserList().toList()
    }
}
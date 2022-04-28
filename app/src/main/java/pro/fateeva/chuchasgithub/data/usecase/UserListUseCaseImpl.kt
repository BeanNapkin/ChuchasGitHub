package pro.fateeva.chuchasgithub.data.usecase

import io.reactivex.rxjava3.core.Single
import pro.fateeva.chuchasgithub.domain.UserRepository
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase

class UserListUseCaseImpl(private val repository: UserRepository) : UserListUseCase {

    override fun getUser(userName: String) : Single<User> {
        return repository.getUser(userName)
    }

    override fun getUserList(): Single<List<User>> {
        return repository.getUserList()
    }

    override fun getReposList(userName: String): Single<List<Repo>> {
        return repository.getReposList(userName)
    }
}
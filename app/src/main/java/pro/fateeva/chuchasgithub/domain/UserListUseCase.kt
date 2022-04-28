package pro.fateeva.chuchasgithub.domain

import io.reactivex.rxjava3.core.Single
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User

interface UserListUseCase {
    fun getUser(userName: String) : Single<User>
    fun getUserList() : Single<List<User>>
    fun getReposList(userName: String): Single<List<Repo>>
}
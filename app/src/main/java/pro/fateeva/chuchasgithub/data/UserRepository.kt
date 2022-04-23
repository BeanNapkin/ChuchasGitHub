package pro.fateeva.chuchasgithub.data

import io.reactivex.rxjava3.core.Single
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User

interface UserRepository {
    fun getUserList() : Single<List<User>>
    fun getReposList(userName: String): Single<List<Repo>>
    fun getUser(userName: String): Single<User>
}
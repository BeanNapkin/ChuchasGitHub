package pro.fateeva.chuchasgithub.data

import io.reactivex.rxjava3.core.Single
import pro.fateeva.chuchasgithub.data.responses.RepoResponseData
import pro.fateeva.chuchasgithub.data.responses.UserResponseData
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User
import pro.fateeva.chuchasgithub.utils.toSingle

class UserRepositoryImpl(
    private val api: GitHubApi
) : UserRepository {

    override fun getUserList(): Single<List<User>> =
        toSingle(api.getUsersList(), { mapUser(it) })

    override fun getReposList(userName: String): Single<List<Repo>> =
        toSingle(api.getListOfRepos(userName), { mapRepo(it) })

    override fun getUser(userName: String): Single<User> =
        toSingle(api.getUser(userName)) { mapUser(it ?: error("User not found")) }


    private fun mapRepo(from: List<RepoResponseData>?): List<Repo> =
        from?.map { it -> Repo(it.name ?: "") } ?: emptyList()

    private fun mapUser(from: UserResponseData): User =
        User(
            id = from.id ?: 0,
            name = from.login ?: "",
            avatar = from.avatar ?: ""
        )

    private fun mapUser(from: List<UserResponseData>?): List<User> =
        from?.map { it ->
            User(
                id = it.id ?: 0,
                avatar = it.avatar ?: "",
                name = it.login ?: ""
            )
        } ?: emptyList()
}
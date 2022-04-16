package pro.fateeva.chuchasgithub.data

import pro.fateeva.chuchasgithub.domain.entities.User

interface UserRepository {
    fun getUsersList() : List<User>
    fun addUser(user: User)
    fun getUser(id: Int): User?
    fun deleteUser(user: User)
    fun deleteAll()
}
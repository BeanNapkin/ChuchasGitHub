package pro.fateeva.chuchasgithub.domain

import pro.fateeva.chuchasgithub.domain.entities.User

interface UserListUseCase {
    fun getUser(position: Int) : User
    fun getUserList() : List<User>
}
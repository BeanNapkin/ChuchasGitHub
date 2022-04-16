package pro.fateeva.chuchasgithub.domain

import pro.fateeva.chuchasgithub.domain.entities.User

interface UserListUseCase {
    fun chooseUser(user: User)
    fun getUserList() : List<User>
}
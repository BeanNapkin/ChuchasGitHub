package pro.fateeva.chuchasgithub.data

import pro.fateeva.chuchasgithub.domain.entities.User

class UserRepositoryImpl : UserRepository {

    private val usersList = mutableListOf<User>(
        User(0, "Bob", "", listOf("Test", "First", "Notes", "BestApp", "BobsApp") ),
        User(1, "Mike", "", listOf("MikesApp") ),
        User(2, "Kate", "", listOf("Katusha", "OloloRepo") ),
        User(3, "Rita_Margarita", "", listOf("ChuchasApp", "SpaceApp", "NotesApp", "MVPapp", "ChuchaChacha") ),
        User(4, "Perfect_sun", "", listOf("Sunshine", "Reggy", "Happy") )
    )

    override fun addUser(user: User) {
        usersList.add(user)
    }

    override fun getUser(id: Int) : User? {
        return usersList[id]
    }

    override fun deleteUser(user: User) {
        usersList.remove(user)
    }

    override fun deleteAll() {
        usersList.removeAll(usersList)
    }
}
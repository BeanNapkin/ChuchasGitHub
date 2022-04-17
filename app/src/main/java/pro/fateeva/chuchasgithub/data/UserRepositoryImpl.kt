package pro.fateeva.chuchasgithub.data

import pro.fateeva.chuchasgithub.domain.entities.User

class UserRepositoryImpl : UserRepository {

    private val usersList = mutableListOf<User>(
        User(0, "Bob", "https://cdn.pixabay.com/photo/2019/10/06/20/01/cat-4531097_1280.jpg", listOf("Test", "First", "Notes", "BestApp", "BobsApp") ),
        User(1, "Mike", "https://p1.pxfuel.com/preview/797/787/747/manul-summer-wild-animal-cat.jpg", listOf("MikesApp") ),
        User(2, "Kate", "https://live.staticflickr.com/3523/3799555301_43dba95c44_b.jpg", listOf("Katusha", "OloloRepo") ),
        User(3, "Rita_Margarita", "https://p2.piqsels.com/preview/327/843/679/harbor-seal-common-seal-seal-white-thumbnail.jpg", listOf("ChuchasApp", "SpaceApp", "NotesApp", "MVPapp", "ChuchaChacha") ),
        User(4, "Perfect_sun", "https://cdn2.picryl.com/photo/2016/07/06/a-touching-moment-nz-fur-seals-2c7843-1024.jpg", listOf("Sunshine", "Reggy", "Happy") )
    )

    override fun getUserList(): List<User> {
        Thread.sleep(3000)
        return usersList.toList()
    }

    override fun addUser(user: User) {
        usersList.add(user)
    }

    override fun getUser(id: Int) : User {
        Thread.sleep(3000)
        return usersList[id]
    }

    override fun deleteUser(user: User) {
        usersList.remove(user)
    }

    override fun deleteAll() {
        usersList.removeAll(usersList)
    }
}
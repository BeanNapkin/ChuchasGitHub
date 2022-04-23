package pro.fateeva.chuchasgithub.domain.entities

data class User(
    val id: Int,
    val name: String,
    val avatar: String,
    val listOfRepos: List<String>
)
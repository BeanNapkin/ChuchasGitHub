package pro.fateeva.chuchasgithub.data.web

import pro.fateeva.chuchasgithub.data.web.responses.RepoResponseDTO
import pro.fateeva.chuchasgithub.data.web.responses.UserResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getUsersList(): Call<List<UserResponseDTO>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Call<UserResponseDTO>

    @GET("users/{user}/repos")
    fun getListOfRepos(@Path("user") user: String): Call<List<RepoResponseDTO>>
}
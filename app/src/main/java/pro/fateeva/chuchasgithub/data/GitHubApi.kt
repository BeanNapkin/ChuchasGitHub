package pro.fateeva.chuchasgithub.data

import pro.fateeva.chuchasgithub.data.responses.RepoResponseData
import pro.fateeva.chuchasgithub.data.responses.UserResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getUsersList(): Call<List<UserResponseData>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Call<UserResponseData>

    @GET("users/{user}/repos")
    fun getListOfRepos(@Path("user") user: String): Call<List<RepoResponseData>>
}
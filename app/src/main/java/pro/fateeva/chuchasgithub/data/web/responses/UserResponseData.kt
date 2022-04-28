package pro.fateeva.chuchasgithub.data.web.responses

import com.google.gson.annotations.SerializedName

data class UserResponseData(
    @field:SerializedName("login") val login: String?,
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("avatar_url") val avatar: String?
)

package pro.fateeva.chuchasgithub.data.web.responses

import com.google.gson.annotations.SerializedName

data class RepoResponseData(
    @SerializedName("name") val name: String?
)

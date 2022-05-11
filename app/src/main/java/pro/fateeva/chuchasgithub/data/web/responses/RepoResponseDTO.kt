package pro.fateeva.chuchasgithub.data.web.responses

import com.google.gson.annotations.SerializedName

data class RepoResponseDTO(
    @SerializedName("name") val name: String?
)

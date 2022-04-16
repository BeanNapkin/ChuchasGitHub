package pro.fateeva.chuchasgithub.ui.userProfile

import androidx.lifecycle.ViewModel
import pro.fateeva.chuchasgithub.domain.UserProfileUseCase

class UserProfileViewModel : ViewModel() {
    lateinit var userProfileUseCase: UserProfileUseCase
}
package pro.fateeva.chuchasgithub.ui.userProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.fateeva.chuchasgithub.domain.UserListUseCase
import pro.fateeva.chuchasgithub.domain.entities.User

class UserProfileViewModel : ViewModel() {
    lateinit var useCase: UserListUseCase

    private val mutableLiveData: MutableLiveData<User> = MutableLiveData()
    val liveData: LiveData<User> = mutableLiveData

    fun getUserLiveData() = liveData

    fun getUser(position: Int) {
        Thread {
            mutableLiveData.postValue(useCase.getUser(position))
        }.start()
    }
}
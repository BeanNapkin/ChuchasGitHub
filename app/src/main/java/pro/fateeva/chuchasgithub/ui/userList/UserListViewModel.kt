package pro.fateeva.chuchasgithub.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.fateeva.chuchasgithub.domain.UserListUseCase
import pro.fateeva.chuchasgithub.domain.entities.User

class UserListViewModel() : ViewModel() {
    lateinit var userListUseCase: UserListUseCase
    private val mutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val liveData: LiveData<List<User>> = mutableLiveData

    fun getUserListLiveData() = liveData

    fun getUserList() {
        Thread {
            mutableLiveData.postValue(userListUseCase.getUserList())
        }.start()
    }
}
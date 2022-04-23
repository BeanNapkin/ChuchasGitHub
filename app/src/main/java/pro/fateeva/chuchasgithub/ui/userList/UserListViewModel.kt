package pro.fateeva.chuchasgithub.ui.userList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pro.fateeva.chuchasgithub.domain.UserListUseCase
import pro.fateeva.chuchasgithub.domain.entities.User

class UserListViewModel(private val state: SavedStateHandle) : ViewModel() {
    lateinit var userListUseCase: UserListUseCase
    val liveData: LiveData<List<User>> = state.getLiveData("userlist")

    init {
        Log.d("UserListVM", "created")
    }

    fun getUserListLiveData() = liveData

    fun getUserList() {
        if (liveData.value == null) {
            Thread {
                state.getLiveData<List<User>>("userlist").postValue(userListUseCase.getUserList())
            }.start()
        }
    }
}
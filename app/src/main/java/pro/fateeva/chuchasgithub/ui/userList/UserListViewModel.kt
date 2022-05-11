package pro.fateeva.chuchasgithub.ui.userList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase
import pro.fateeva.chuchasgithub.domain.entities.User

class UserListViewModel(private val state: SavedStateHandle) : ViewModel(), KoinComponent {

    private val userListUseCase: UserListUseCase by inject()
    val liveData: LiveData<List<User>> = state.getLiveData("userlist")
    private val compositeDisposable = CompositeDisposable()

    init {
        Log.d("UserListVM", "created")
    }

    fun getUserListLiveData() = liveData

    fun getUserList() {
        val isDataCached = liveData.value != null
        if (isDataCached) return
        userListUseCase.getUserList()
            .subscribeBy(
                onSuccess = {
                    state.getLiveData<List<User>>("userlist").postValue(it)
                },
                onError = {
                    Log.e("UserListViewModel", "Failed to get user list $it")
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
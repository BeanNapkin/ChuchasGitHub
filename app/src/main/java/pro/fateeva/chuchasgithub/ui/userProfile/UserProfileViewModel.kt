package pro.fateeva.chuchasgithub.ui.userProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.fateeva.chuchasgithub.domain.usecase.UserListUseCase
import pro.fateeva.chuchasgithub.domain.entities.Repo
import pro.fateeva.chuchasgithub.domain.entities.User

class UserProfileViewModel : ViewModel(), KoinComponent {
    private val useCase: UserListUseCase by inject()

    private val mutableLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User> = mutableLiveData

    private val reposMutableLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    val reposLiveData: LiveData<List<Repo>> = reposMutableLiveData

    private val compositeDisposable = CompositeDisposable()

    fun getUser(userName: String) {
        useCase.getUser(userName)
            .subscribeBy(
                onSuccess = {
                    mutableLiveData.postValue(it)
                },
                onError = {
                    Log.e("UserProfileViewModel", "Failed to get user info $it")
                }
            ).addTo(compositeDisposable)
    }

    fun getUsersRepo(userName: String) {
        useCase.getReposList(userName)
            .subscribeBy(
                onSuccess = {
                    reposMutableLiveData.postValue(it)
                },
                onError = {
                    Log.e("UserProfileViewModel", "Failed to get users repo $it")
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
package pro.fateeva.chuchasgithub.dagger

import dagger.Component
import pro.fateeva.chuchasgithub.ui.userList.UserListViewModel
import pro.fateeva.chuchasgithub.ui.userProfile.UserProfileViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppDependenciesModule::class])
interface AppDependenciesComponent {
    fun inject(userListViewModel: UserListViewModel)
    fun inject(userProfileViewModel: UserProfileViewModel)
}
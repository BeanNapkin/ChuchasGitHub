package pro.fateeva.chuchasgithub

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pro.fateeva.chuchasgithub.dagger.AppDependenciesComponent
import pro.fateeva.chuchasgithub.dagger.DaggerAppDependenciesComponent

class App : Application() {
    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()

        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .build()

        //        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            modules(appModule)
//        }
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }

package uz.test.listapp


import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import uz.test.listapp.modules.appModule
import uz.test.listapp.modules.databaseModule
import uz.test.listapp.modules.viewModel

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        mContext=this@MainApplication
        mScope= CoroutineScope(SupervisorJob())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(appModule,databaseModule, viewModel))

            //Start network callback
        }


    }




    companion object{
        lateinit var mContext : Context
        lateinit var mScope : CoroutineScope
    }
}
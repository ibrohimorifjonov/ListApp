package uz.test.listapp.modules

import org.koin.dsl.module
import uz.test.listapp.network.ListDao
import uz.test.listapp.network.ListDatabase

val databaseModule= module {
    single<ListDao> {
        val database = get<ListDatabase>()
        database.listDao()
    }

    single<ListDatabase> {
        ListDatabase.getDatabase(get())
    }
}
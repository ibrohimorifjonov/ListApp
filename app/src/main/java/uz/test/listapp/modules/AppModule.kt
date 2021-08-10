package uz.test.listapp.modules

import org.koin.dsl.module
import uz.test.listapp.network.ListRepository

val appModule= module{
    single {  ListRepository(get()) }
}
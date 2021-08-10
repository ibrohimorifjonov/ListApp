package uz.test.listapp.network

import kotlinx.coroutines.flow.Flow
import uz.test.listapp.data.ListOfWork


class ListRepository(private val listDao: ListDao) {

    suspend fun insertData(list: ListOfWork){
        return listDao.insert(list)
    }

    suspend fun deleteTask(id:Int){
        listDao.deleteTask(id)
    }

    suspend fun updateState(state:String,id:Int){
        listDao.updateState(state, id)
    }

    val list:  Flow<List<ListOfWork>> = listDao.getList()
    val inProgressTask : Flow<List<ListOfWork>>  = listDao.getTaskInState()
    val doneTasks : Flow<List<ListOfWork>> = listDao.getDoneTasks()

    suspend fun getTask(id:Int):ListOfWork{
        return listDao.getTask(id)
    }

    suspend fun updateTask(date:String,name:String, id: Int){
        listDao.updateTask(date, name, id)
    }
}
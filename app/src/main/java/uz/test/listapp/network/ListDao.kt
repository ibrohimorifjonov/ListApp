package uz.test.listapp.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.test.listapp.data.ListOfWork

@Dao
interface ListDao {
    @Query("DELETE FROM listofwork")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list:ListOfWork)

    @Query("SELECT * FROM listofwork")
    fun getList() : Flow<List<ListOfWork>>

    @Query("SELECT * FROM listofwork WHERE state=:state")
    fun getTaskInState(state: String="in progress"): Flow<List<ListOfWork>>

    @Query("SELECT * FROM listofwork WHERE state=:state")
    fun getDoneTasks(state: String="done"): Flow<List<ListOfWork>>

    @Query("DELETE FROM listofwork WHERE id=:id")
    suspend fun deleteTask(id:Int)

    @Query("UPDATE listofwork SET state=:state WHERE id=:id")
    suspend fun updateState(state:String,id: Int)

    @Query("SELECT * FROM listofwork WHERE id=:id")
    suspend fun getTask(id:Int):ListOfWork

    @Query("UPDATE listofwork SET date=:date, name=:name WHERE id=:id")
    suspend fun updateTask(date:String,name:String,id: Int)
}
package uz.test.listapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ListOfWork(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name:String,
    val date:String,
    val state:String
)
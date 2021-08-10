package uz.test.listapp.ui.updatetask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.network.ListRepository

class UpdateTaskViewModel(val repository: ListRepository):ViewModel() {
    val taskResponse=MutableLiveData<ListOfWork>()

    fun getTask(id:Int)=viewModelScope.launch {
        taskResponse.value=repository.getTask(id)
    }

    fun updateTask(date:String,name:String,id:Int)=viewModelScope.launch {
        repository.updateTask(date, name, id)
    }

}
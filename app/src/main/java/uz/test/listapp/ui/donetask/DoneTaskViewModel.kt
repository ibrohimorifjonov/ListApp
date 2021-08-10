package uz.test.listapp.ui.donetask

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.network.ListRepository

class DoneTaskViewModel(val repository: ListRepository) : ViewModel() {

    val data: LiveData<List<ListOfWork>> = repository.doneTasks.asLiveData()

    fun deleteTask(id:Int)=viewModelScope.launch {
        repository.deleteTask(id)
    }

    fun updateState(state:String,id: Int)=viewModelScope.launch {
        repository.updateState(state, id)
    }
}
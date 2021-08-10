package uz.test.listapp.ui.inprogress

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.network.ListRepository

class InProgressViewModel(val repository: ListRepository) : ViewModel() {

    val data: LiveData<List<ListOfWork>> = repository.inProgressTask.asLiveData()
    fun deleteTask(id:Int)=viewModelScope.launch {
        repository.deleteTask(id)
    }

    fun updateState(state:String,id: Int)=viewModelScope.launch {
        repository.updateState(state, id)
    }
}
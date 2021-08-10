package uz.test.listapp.ui.alltask

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.network.ListRepository

class HomeViewModel(val repository: ListRepository) : ViewModel() {

    val data : LiveData<List<ListOfWork>> = repository.list.asLiveData()

    fun deleteTask(id:Int)=viewModelScope.launch {
        repository.deleteTask(id)
    }

    fun updateState(state:String,id: Int)=viewModelScope.launch {
        repository.updateState(state, id)
    }
}
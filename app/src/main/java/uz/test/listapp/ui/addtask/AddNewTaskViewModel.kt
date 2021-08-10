package uz.test.listapp.ui.addtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.network.ListRepository

class AddNewTaskViewModel(val repository: ListRepository):ViewModel() {
    val insertDataResponse= MutableLiveData<Any>()

    fun insertData(list: ListOfWork)=viewModelScope.launch {
        insertDataResponse.value=repository.insertData(list)
    }
}
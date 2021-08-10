package uz.test.listapp.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.test.listapp.ui.addtask.AddNewTaskViewModel
import uz.test.listapp.ui.alltask.HomeViewModel
import uz.test.listapp.ui.donetask.DoneTaskViewModel
import uz.test.listapp.ui.inprogress.InProgressViewModel
import uz.test.listapp.ui.updatetask.UpdateTaskViewModel

val viewModel= module {
    viewModel { HomeViewModel(get ()) }
    viewModel { AddNewTaskViewModel(get()) }
    viewModel { UpdateTaskViewModel(get ()) }
    viewModel { InProgressViewModel(get()) }
    viewModel { DoneTaskViewModel(get()) }
}
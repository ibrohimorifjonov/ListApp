package uz.test.listapp.ui.addtask

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.test.listapp.R
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.databinding.FragmentAddNewTaskBinding
import uz.test.listapp.utils.showSnackbar
import java.util.*


class AddNewTaskFragment : Fragment() {

    private var _binding:FragmentAddNewTaskBinding?=null
    private val binding get() =_binding!!
    private val addNewTaskViewModel:AddNewTaskViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddNewTaskBinding.inflate(inflater, container, false)
        clickListener()
        return binding.root
    }

    fun clickListener(){
        val monthName= resources.getStringArray(R.array.months)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        var date=""
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.dateEditText.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.dateEditText.setText(requireContext().getString(R.string.dateFormat, dayOfMonth ,monthName[monthOfYear] ,year))
                date=requireContext().getString(R.string.dateFormattedWithDot,dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString())
            }, year, month, day)

            datePicker.show()
        }

        binding.createTask.setOnClickListener {
            if(binding.taskNameEditText.text.toString().isNotEmpty()&&date.isNotEmpty()){
                addNewTaskViewModel.insertData(ListOfWork(date = date, name = binding.taskNameEditText.text.toString(),state = "in progress"))
                findNavController().popBackStack()
                date=""
            }else{
                showSnackbar("Заполните все поля")
            }
        }

    }


}
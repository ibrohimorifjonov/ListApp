package uz.test.listapp.ui.updatetask

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.test.listapp.R
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.databinding.FragmentUpdateTaskBinding
import uz.test.listapp.utils.showSnackbar
import java.util.*


class UpdateTaskFragment : Fragment() {
    private var _binding:FragmentUpdateTaskBinding?=null
    private val binding get() = _binding!!
    private var param1: Int? = null
    private var date=""
    private val viewModel:UpdateTaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt("ID")
        }
        param1?.let { viewModel.getTask(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentUpdateTaskBinding.inflate(inflater,container,false)

        viewModel.taskResponse.observe(
            viewLifecycleOwner, Observer { it->
                it?.let {
                    binding.taskNameEditText.setText(it.name)
                    binding.dateEditText.setText(dateFormat(it.date))
                }
            }
        )

        clickListener()

        return binding.root
    }

    fun dateFormat(date:String):String{
        this.date=date
        val monthName = resources.getStringArray(R.array.months)

        val splitData=date.split(".")
        return requireContext().getString(R.string.dateAllString,splitData[0],monthName[splitData[1].toInt()-1],splitData[2])
    }

    fun clickListener(){
        val monthName= resources.getStringArray(R.array.months)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)

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

        binding.updateTask.setOnClickListener {
            if(binding.taskNameEditText.text.toString().isNotEmpty()&&date.isNotEmpty()){
                param1?.let { it1 ->
                    viewModel.updateTask(date, name = binding.taskNameEditText.text.toString(),
                        it1
                    )
                }
                findNavController().popBackStack()
                date=""
            }else{
                showSnackbar("Заполните все поля")
            }
        }

    }
}
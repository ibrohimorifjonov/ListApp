package uz.test.listapp.ui.donetask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.test.listapp.R
import uz.test.listapp.databinding.FragmentDashboardBinding
import uz.test.listapp.ui.alltask.AllTaskAdapter
import uz.test.listapp.utils.SpacesItemDecoration
import uz.test.listapp.utils.toDp

class DoneTasksFragment : Fragment() {
    private var _binding:FragmentDashboardBinding?=null
    private val binding get() = _binding!!
    private val viewModel: DoneTaskViewModel by viewModel()
    private lateinit var adapter: AllTaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter= AllTaskAdapter()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDashboardBinding.inflate(inflater,container,false)

        setUpRecycler()
        viewModel.data.observe(
                viewLifecycleOwner, Observer{
            it?.let {
                adapter.updateList(it)
            }
        }
        )

        adapter.onDeleteClick={
            viewModel.deleteTask(it.id)
        }

        adapter.onStateClick={
            if (it.state=="done"){
                viewModel.updateState("in progress",it.id)
            }else{
                viewModel.updateState("done",it.id)
            }
        }

        adapter.onEditClick={
            val bundle=Bundle()
            bundle.putInt("ID",it.id)
            findNavController().navigate(R.id.updateTaskFragment,bundle)
        }
        return binding.root
    }

    fun setUpRecycler(){
        binding.doneTasksRecycler.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.doneTasksRecycler.addItemDecoration(SpacesItemDecoration(toDp(16),span = 1))
        binding.doneTasksRecycler.adapter=adapter
    }
}
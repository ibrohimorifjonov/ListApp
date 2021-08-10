package uz.test.listapp.ui.alltask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.test.listapp.R
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.databinding.FragmentHomeBinding
import uz.test.listapp.utils.SpacesItemDecoration
import uz.test.listapp.utils.toDp

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private  val homeViewModel: HomeViewModel by viewModel()
    private lateinit var adapter:AllTaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter= AllTaskAdapter()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        clickListener()
        setUpRecycler()

        homeViewModel.data.observe(
            viewLifecycleOwner, Observer { i->
                i?.let {
                    adapter.updateList(it)
                }
            }
        )

        adapter.onDeleteClick={
            homeViewModel.deleteTask(it.id)
        }

        adapter.onStateClick={
            if (it.state=="done"){
                homeViewModel.updateState("in progress",it.id)
            }else{
                homeViewModel.updateState("done",it.id)
            }
        }

        adapter.onEditClick={
            val bundle=Bundle()
            bundle.putInt("ID",it.id)
            findNavController().navigate(R.id.updateTaskFragment,bundle)
        }
        return binding.root
    }

    fun clickListener(){
        binding.addNewTask.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_addNewTaskFragment)
        }
    }

    fun setUpRecycler(){
        binding.allTaskRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.allTaskRecycler.addItemDecoration(SpacesItemDecoration(toDp(16),span = 1))
        binding.allTaskRecycler.adapter=adapter
    }

}
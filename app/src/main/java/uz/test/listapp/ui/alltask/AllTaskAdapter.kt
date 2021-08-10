package uz.test.listapp.ui.alltask

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.test.listapp.data.ListOfWork
import uz.test.listapp.databinding.ItemTaskBinding

class AllTaskAdapter:RecyclerView.Adapter<AllTaskAdapter.ViewHolder>() {

    var list:List<ListOfWork>?=ArrayList()
    var onEditClick:((ListOfWork)->Unit)?=null
    var onDeleteClick:((ListOfWork)->Unit)?=null
    var onStateClick:((ListOfWork)->Unit)?=null

    fun updateList(list:List<ListOfWork>){
        this.list=list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTaskAdapter.ViewHolder {
        val binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllTaskAdapter.ViewHolder, position: Int) {
       holder.bind(list!![position])
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    inner class ViewHolder(var binding: ItemTaskBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(d:ListOfWork){
            binding.textName.text=d.name
            binding.textDate.text=d.date
            binding.taskProgress.isChecked = d.state=="done"
        }

        init {

            binding.editTask.setOnClickListener {
                onEditClick?.invoke(list!![adapterPosition])
            }

            binding.deleteTask.setOnClickListener {
                onDeleteClick?.invoke(list!![adapterPosition])
            }

            binding.taskProgress.setOnClickListener {
               onStateClick?.invoke(list!![adapterPosition])
            }
        }
    }
}
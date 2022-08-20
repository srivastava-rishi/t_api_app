package com.rsstudio.tamasha.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rsstudio.tamasha.R
import com.rsstudio.tamasha.data.network.model.Data
import com.rsstudio.tamasha.data.network.model.Employee

class MainAdapter(
    private var context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() , Filterable {

    private var list: MutableList<Employee> = mutableListOf()

    private var filteredEmployeeList: MutableList<Data> = mutableListOf()

    var logTag = "@MainAdapter"

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvId: TextView = view.findViewById(R.id.tvEmployeeId)
        var tvName: TextView = view.findViewById(R.id.tvEmployeeName)
        var tvAge: TextView = view.findViewById(R.id.tvEmployeeAge)
        var tvSalary: TextView = view.findViewById(R.id.tvEmployeeSalary)
        var container: CardView = view.findViewById(R.id.cvEmployeeCard)
        var employeeAgeContainer: LinearLayout = view.findViewById(R.id.llEmployeeAge)

        @SuppressLint("SetTextI18n")
        fun onBind(item: Data,position: Int) {

            tvId.text = item.id.toString()
            tvName.text = item.employee_name
            tvAge.text = item.employee_age.toString()
            tvSalary.text = item.employee_salary.toString()

            employeeAgeContainer.visibility = if (item.visibility) View.VISIBLE else View.GONE

            container.setOnClickListener {
                item.visibility = !item.visibility
                notifyItemChanged(position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_employee_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = filteredEmployeeList[position]
        if (holder is MainAdapter.ItemViewHolder) {
            holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.anim_fade_scale)
            holder.onBind(item,position)
        }
    }

    fun submitList(newList: List<Employee>) {
        list.clear()
        filteredEmployeeList.clear()
        list.addAll(newList)
        filteredEmployeeList.addAll(list[0].data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (filteredEmployeeList.size != 0) {
            return filteredEmployeeList.size
        }
        return 0
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charString = constraint?.toString() ?: ""

                if(charString.isEmpty()){
                    filteredEmployeeList.clear()
                    filteredEmployeeList.addAll(list[0].data)
                } else{

                    var filteredList:  MutableList<Data> = mutableListOf()

                    list[0].data.filter {
                        (it.employee_name.lowercase().contains(constraint.toString().lowercase().trim()))
                    }.forEach{ filteredList.add(it)}
                    filteredEmployeeList = filteredList
                }

                return FilterResults().apply { values = filteredEmployeeList }

            }
            override fun publishResults(constraint: CharSequence, results: FilterResults?) {
                if (results!!.values != null) {
                    filteredEmployeeList = results.values as MutableList<Data>
                    notifyDataSetChanged()
                }

            }
        }
    }

}
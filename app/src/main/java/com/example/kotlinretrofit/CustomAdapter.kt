package com.example.kotlinretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinretrofit.CustomAdapter.CustomViewHolder
import com.example.kotlinretrofit.databinding.RowLayoutBinding

class CustomAdapter(private val dataList: List<RetroUser?>) :
    RecyclerView.Adapter<CustomViewHolder>() {
    var binding: RowLayoutBinding? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_layout,
            parent,
            false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txtTitle.text = dataList[position]?.name
        holder.txtEmail.text = dataList[position]?.email
    }

    inner class CustomViewHolder(itemBind: RowLayoutBinding?) :
        RecyclerView.ViewHolder(itemBind!!.root) {
        var txtTitle: TextView = binding!!.RetroText
        var txtEmail: TextView = binding!!.emailText

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
package com.faysal.assignementapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.faysal.assignementapp.R
import com.faysal.assignementapp.data.models.User
import com.faysal.assignementapp.databinding.ItemUsersLayoutBinding

class AdapterUserList : RecyclerView.Adapter<AdapterUserList.HolderUserList>() {

    val userListData: MutableList<User> = mutableListOf()


    lateinit var mOnItemClickLister: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(user : User, pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickLister = listener
    }



    inner class HolderUserList(val binding : ItemUsersLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderUserList {
        val binding =
            ItemUsersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderUserList(binding)
    }

    override fun onBindViewHolder(holder: HolderUserList, position: Int) {
        val user = userListData.get(position)
        val binding = holder.binding

        user.name?.let { binding.tvName.text = "${it.title} ${it.first}" }
        user.location?.let { binding.tvCountry.text = "${it.country}" }
        user.picture?.let {
            binding.itemIcon.load(it.medium) {
                transformations(CircleCropTransformation())
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)

            }
        }

        binding.root.setOnClickListener { mOnItemClickLister.onItemClicked(user,position) }

    }

    fun updateUserList(nlist : List<User>){
        userListData.addAll(nlist)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userListData.size
}
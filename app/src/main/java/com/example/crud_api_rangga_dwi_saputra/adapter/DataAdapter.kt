package com.example.crud_api_rangga_dwi_saputra.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_api_rangga_dwi_saputra.databinding.ItemDataBinding
import com.example.crud_api_rangga_dwi_saputra.model.DataItem

class DataAdapter(val data: List<DataItem>? , private val click: onClickItem) : RecyclerView.Adapter<DataAdapter.MyHolder>() {
    // Karena menggunakan ViewBinding, kita tidak perlu inisialisasi binding di sini secara global
    // tapi dilakukan di onCreateViewHolder

    inner class MyHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(get: DataItem?) {
            binding.tvName.text = get?.staffName
            binding.tvPhone.text = get?.staffHp
            binding.tvAddress.text = get?.staffAlamat

            // Click Listener untuk Update (Klik Nama/Item)
            binding.tvName.setOnClickListener() {
                click.clicked(get)
            }

            // Click Listener untuk Delete (Klik Tombol Hapus)
            binding.btnHapus.setOnClickListener {
                click.delete(get)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
    }

    interface onClickItem{
        fun clicked (item: DataItem?)
        fun delete(item: DataItem?)
    }
}
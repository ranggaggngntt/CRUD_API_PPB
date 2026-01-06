package com.example.crud_api_rangga_dwi_saputra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_api_rangga_dwi_saputra.databinding.ActivityUpdateAddBinding
import com.example.crud_api_rangga_dwi_saputra.model.DataItem
import com.example.crud_api_rangga_dwi_saputra.presenter.CrudView
import com.example.crud_api_rangga_dwi_saputra.presenter.Presenter2

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter2
    private lateinit var binding: ActivityUpdateAddBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter2(this)

        val itemData = intent.getSerializableExtra("dataItem") as DataItem?

        if (itemData == null) {
            binding.btnAction.text = "Tambah"
            binding.btnAction.setOnClickListener {
                presenter.addData(
                    binding.etName.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etAlamat.text.toString(),
                    binding.etPekerjaan.text.toString(),
                    binding.etHobi.text.toString()
                )
            }
        } else {
            binding.btnAction.text = "Update"

            binding.etName.setText(itemData.staffName)
            binding.etPhone.setText(itemData.staffHp)
            binding.etAlamat.setText(itemData.staffAlamat)
            binding.etPekerjaan.setText(itemData.staffPekerjaan)
            binding.etHobi.setText(itemData.staffHobi)

            binding.btnAction.setOnClickListener {
                presenter.updateData(
                    itemData.staffId ?: "",
                    binding.etName.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etAlamat.text.toString(),
                    binding.etPekerjaan.text.toString(),
                    binding.etHobi.text.toString()
                )
            }
        }
    }

    override fun successAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun errorAdd(msg: String) {}

    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorUpdate(msg: String) {}

    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}

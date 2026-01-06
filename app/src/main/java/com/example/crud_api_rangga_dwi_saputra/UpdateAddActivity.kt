package com.example.crud_api_rangga_dwi_saputra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_api_rangga_dwi_saputra.databinding.ActivityUpdateAddBinding
import com.example.crud_api_rangga_dwi_saputra.model.DataItem
import com.example.crud_api_rangga_dwi_saputra.presenter.CrudView
import com.example.crud_api_rangga_dwi_saputra.presenter.Presenter2

@Suppress("SENSELESS_COMPARISON") // Menekan peringatan Kotlin untuk perbandingan null
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter2
    private lateinit var binding : ActivityUpdateAddBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi binding untuk layout activity_update_add
        binding = ActivityUpdateAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter2(this)

        // Cek apakah ada data item yang dikirim (berarti operasi UPDATE)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if (itemDataItem == null) {
            // MODE ADD (TAMBAH DATA)
            binding.btnAction.text = "Tambah"
            binding.btnAction.setOnClickListener() {
                presenter.addData(
                    binding.etName.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etAlamat.text.toString()
                )
            }
        } else if (itemDataItem != null) {
            // MODE UPDATE (EDIT DATA)
            binding.btnAction.text = "Update"
            val item = itemDataItem as DataItem?

            // Isi EditText dengan data lama
            binding.etName.setText(item?.staffName.toString())
            binding.etPhone.setText(item?.staffHp.toString())
            binding.etAlamat.setText(item?.staffAlamat.toString())

            binding.btnAction.setOnClickListener() {
                presenter.updateData(
                    item?.staffId ?: "", // Gunakan ID lama
                    binding.etName.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etAlamat.text.toString()
                )
                // Pindah ke MainActivity dilakukan di callback onSuccessUpdate
            }
        }
    }

    // Callback saat Add berhasil
    override fun successAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    // Callback saat Add gagal
    override fun errorAdd(msg: String) {}

    // Callback saat Update berhasil
    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    // Callback saat Update gagal
    override fun onErrorUpdate(msg: String) {}

    // Implementasi kosong untuk fungsi Get dan Delete (karena dijalankan di MainActivity)
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}
package com.example.crud_api_rangga_dwi_saputra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_api_rangga_dwi_saputra.adapter.DataAdapter
import com.example.crud_api_rangga_dwi_saputra.databinding.ActivityMainBinding
import com.example.crud_api_rangga_dwi_saputra.model.DataItem
import com.example.crud_api_rangga_dwi_saputra.presenter.CrudView
import com.example.crud_api_rangga_dwi_saputra.presenter.Presenter
import com.example.crud_api_rangga_dwi_saputra.ui.theme.CRUD_API_Rangga_Dwi_SaputraTheme


class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi binding untuk layout activity_main
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi presenter dan panggil fungsi getData untuk menampilkan data awal
        presenter = Presenter(this)
        presenter.getData()

        // Set listener untuk tombol Tambah
        binding.btnTambah.setOnClickListener {
            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
            finish()
        }
    }

    // Callback saat data berhasil diambil (READ)
    override fun onSuccessGet(data: List<DataItem>?) {
        // Hubungkan data ke RecyclerView melalui DataAdapter
        binding.rvCategory.adapter = DataAdapter(data, object : DataAdapter.onClickItem {

            // Aksi saat item di klik (untuk Update/Edit)
            override fun clicked(item: DataItem?) {
                val bundle = Bundle()
                bundle.putSerializable("dataItem", item)
                val intent = Intent(applicationContext, UpdateAddActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            // Aksi saat tombol Hapus di klik (untuk Delete)
            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                // Setelah delete, refresh MainActivity
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })
    }

    // Callback saat gagal mengambil data
    override fun onFailedGet(msg: String) {
        // Log atau tampilkan pesan error jika diperlukan
    }

    // Callback saat Delete berhasil
    override fun onSuccessDelete(msg: String) {
        // Refresh data setelah berhasil dihapus
        presenter.getData()
    }

    // Callback saat Delete gagal
    override fun onErrorDelete(msg: String) {
        Toast.makeText(
            this,
            "Delete Tidak Berhasil",
            Toast.LENGTH_SHORT
        ).show()
    }

    // Implementasi kosong untuk fungsi Add dan Update (karena dijalankan di UpdateAddActivity)
    override fun successAdd(msg: String) { }
    override fun errorAdd(msg: String) { }
    override fun onSuccessUpdate(msg: String) { }
    override fun onErrorUpdate(msg: String) { }
}
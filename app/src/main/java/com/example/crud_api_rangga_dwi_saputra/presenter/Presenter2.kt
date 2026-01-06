package com.example.crud_api_rangga_dwi_saputra.presenter

import com.example.crud_api_rangga_dwi_saputra.UpdateAddActivity
import com.example.crud_api_rangga_dwi_saputra.model.ResultStatus
import com.example.crud_api_rangga_dwi_saputra.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter2 (val crudView: UpdateAddActivity) {
    //Add data
    fun addData(name : String, hp : String, alamat : String){
        NetworkConfig.getService()
            .addStaff(name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }
            })
    }

    //Update Data
    fun updateData(id: String, name: String, hp: String, alamat: String){
        NetworkConfig.getService()
            .updateStaff(id, name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}
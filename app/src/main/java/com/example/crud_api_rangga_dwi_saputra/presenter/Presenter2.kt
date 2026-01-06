package com.example.crud_api_rangga_dwi_saputra.presenter

import com.example.crud_api_rangga_dwi_saputra.UpdateAddActivity
import com.example.crud_api_rangga_dwi_saputra.model.ResultStatus
import com.example.crud_api_rangga_dwi_saputra.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter2(private val crudView: UpdateAddActivity) {

    fun addData(
        name: String,
        hp: String,
        alamat: String,
        pekerjaan: String,
        hobi: String
    ) {
        NetworkConfig.getService()
            .addStaff(name, hp, alamat, pekerjaan, hobi)
            .enqueue(object : retrofit2.Callback<ResultStatus> {

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage ?: "Error")
                }

                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    } else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }
            })
    }

    fun updateData(
        id: String,
        name: String,
        hp: String,
        alamat: String,
        pekerjaan: String,
        hobi: String
    ) {
        NetworkConfig.getService()
            .updateStaff(id, name, hp, alamat, pekerjaan, hobi)
            .enqueue(object : retrofit2.Callback<ResultStatus> {

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage ?: "Error")
                }

                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}

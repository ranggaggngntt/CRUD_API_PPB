package com.example.crud_api_rangga_dwi_saputra.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable {

    @SerializedName("staff_id")
    val staffId: String? = null

    @SerializedName("staff_name")
    val staffName: String? = null

    @SerializedName("staff_hp")
    val staffHp: String? = null

    @SerializedName("staff_alamat")
    val staffAlamat: String? = null

    @SerializedName("staff_pekerjaan")
    val staffPekerjaan: String? = null

    @SerializedName("staff_hobi")
    val staffHobi: String? = null
}

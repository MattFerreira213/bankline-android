package com.example.bankline_android.data.remote

import com.example.bankline_android.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {

    @GET("movimentações/{id}")
    fun findBankStatement(@Path("id") accountHolderId: Int): List<Movimentacao>
}
package com.example.bankline_android.ui.Statement

import androidx.lifecycle.ViewModel
import com.example.bankline_android.data.BanklineRepository

class BanklineStatementViewModel : ViewModel()  {

   fun findBankStatement(accountHolderId: Int) =
       BanklineRepository.findBankStatement(accountHolderId)
}
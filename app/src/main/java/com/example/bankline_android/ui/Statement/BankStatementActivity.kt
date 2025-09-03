package com.example.bankline_android.ui.Statement

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bankline_android.databinding.ActivityBankStatementBinding
import com.example.bankline_android.domain.Correntista

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "com.example.bankline_android.ui.welcome.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("TESTE", "onCreate: $accountHolder")
    }
}
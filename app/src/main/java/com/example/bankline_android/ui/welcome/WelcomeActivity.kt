package com.example.bankline_android.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bankline_android.databinding.ActivityWelcomeBinding
import com.example.bankline_android.domain.Correntista
import com.example.bankline_android.ui.Statement.BankStatementActivity


class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val accountHolderId = binding.edtNumberAccount.text.toString().toInt()
            val accountHolder = Correntista(accountHolderId)

            if (validateAccountHolderIsNotEmpty(accountHolderId.toString())) {
                val intent = Intent(this, BankStatementActivity::class.java).apply {
                    putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
                }
                startActivity(intent)
            }

        }
    }

    private fun validateAccountHolderIsNotEmpty(accountHolderNumberValidator: String): Boolean {
        if (accountHolderNumberValidator.isEmpty()) {
            binding.edtNumberAccount.error =
                "O campo Account Holder está vazio. Por favor informe o número da conta."
            return false
        }
        return true
    }

    private fun validateAccountHolderExist(
        accountHolderNumberValidator: String,
        accountHolderIdApi: String
    ): Boolean {
        if (accountHolderNumberValidator != accountHolderIdApi) {
            binding.edtNumberAccount.error =
                "O campo Account Holder está incorreto. Por favor informe o número da conta correto."
            return true
        }
        return false
    }
}
package com.example.bankline_android.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            val validate = validateAccountHolder(binding.edtNumberAccount.text.toString())

            if (validate) {
                val accountHolderId = binding.edtNumberAccount.text.toString().toInt()
                val accountHolder = Correntista(accountHolderId)

                val intent = Intent(this, BankStatementActivity::class.java).apply {
                    putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
                }
                startActivity(intent)
            }

        }
    }

    private fun validateAccountHolder(accountHolderNumberValidator: String): Boolean {
        if (accountHolderNumberValidator.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor, informe um valor valido.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}
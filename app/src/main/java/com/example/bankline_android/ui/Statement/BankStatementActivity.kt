package com.example.bankline_android.ui.Statement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankline_android.data.State
import com.example.bankline_android.databinding.ActivityBankStatementBinding
import com.example.bankline_android.domain.Correntista
import com.google.android.material.snackbar.Snackbar


class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER =
            "com.example.bankline_android.ui.welcome.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<BanklineStatementViewModel>()

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER)
            ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)
        findBankStatement()

        binding.srBankStatement.setOnRefreshListener {
            findBankStatement()
        }

    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when (state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srBankStatement.isRefreshing = false
                }

                is State.Error -> {
                    state.message?.let {
                        Snackbar.make(
                            binding.rvBankStatement,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    binding.srBankStatement.isRefreshing = false
                }

                State.Wait -> {
                    binding.srBankStatement.isRefreshing = true
                }
            }
        }
    }
}
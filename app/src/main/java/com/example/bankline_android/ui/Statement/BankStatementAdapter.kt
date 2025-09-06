package com.example.bankline_android.ui.Statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankline_android.R
import com.example.bankline_android.databinding.BankStatementItemBinding
import com.example.bankline_android.domain.Movimentacao
import com.example.bankline_android.domain.TipoMovimentacao

/**
 * Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br#kotlin
 */
class BankStatementAdapter(private val dataSet: List<Movimentacao>) :
    RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BankStatementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movimentacao) = with(binding) {
            txtDescription.text = item.descricao
            txtDatetime.text = item.dataHora
            txtValue.text = item.valor.toString()

            val typeIcon =
                if (TipoMovimentacao.RECEITA == item.tipo) R.drawable.ic_money_in else R.drawable.ic_money_out
            imgIcon.setImageResource(typeIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}
package br.com.lab.impacta.account.application.adapter

import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse

import br.com.lab.impacta.account.model.Account




class AccountAdapter {
    fun toDtoBalance(account: Account): AccountBalanceResponse? {
        return AccountBalanceResponse(account.id, account.balance)
    }
}
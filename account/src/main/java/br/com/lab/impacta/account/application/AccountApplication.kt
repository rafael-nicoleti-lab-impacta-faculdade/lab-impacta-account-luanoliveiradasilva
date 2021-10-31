package br.com.lab.impacta.account.application

import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse


interface AccountApplication {

    fun balance(accountId: Long?): AccountBalanceResponse?

    fun debit(accountId: Long?, debitAccountRequest: DebitAccountRequest?): DebitAccountResponse?
}
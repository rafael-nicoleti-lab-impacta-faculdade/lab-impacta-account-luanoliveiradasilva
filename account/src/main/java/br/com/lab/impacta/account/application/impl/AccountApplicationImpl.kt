package br.com.lab.impacta.account.application.impl

import br.com.lab.impacta.account.application.AccountApplication
import br.com.lab.impacta.account.application.adapter.AccountAdapter
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse
import br.com.lab.impacta.account.model.Account
import br.com.lab.impacta.account.service.AccountService
import org.springframework.beans.factory.annotation.Autowired


class AccountApplicationImpl: AccountApplication {

    @Autowired
    lateinit var accountService: AccountService

    override fun balance(accountId: Long?): AccountBalanceResponse? {
        var account: Account? = accountId?.let { accountService.findAccount(it) }

        return AccountAdapter.toDtoBalance(account)
    }

    override fun debit(accountId: Long?, debitAccountRequest: DebitAccountRequest?): DebitAccountResponse? {
        accountService.debitAccount(accountId, debitAccountRequest.valueOfDebit())

        return DebitAccountResponse(true)
    }


}
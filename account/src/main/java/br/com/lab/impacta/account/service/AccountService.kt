package br.com.lab.impacta.account.service

import br.com.lab.impacta.account.model.Account

interface AccountService {

    fun findAccount(accountId:Long) : Account

    fun debitAccount(accountId:Long, valueOfDebit:Double )
}
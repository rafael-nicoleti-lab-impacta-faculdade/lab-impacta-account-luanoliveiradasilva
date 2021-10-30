package br.com.lab.impacta.account.service.impl

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException
import br.com.lab.impacta.account.model.Account
import br.com.lab.impacta.account.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import br.com.lab.impacta.account.repository.AccountRepository
import org.springframework.beans.factory.annotation.Value
import java.util.*

@Service
class AccountServiceImpl : AccountService {

    @Autowired
    lateinit var accountRepository: AccountRepository
    @Value("\${lab.account.exceptions.account-dont-exists-message}")
    private lateinit var messageExceptionAccountDontExists: String

    @Value("\${lab.account.exception.account-dont-exists-description}")
    private lateinit var descriptionExceptionAccountDontExistsException: String

    override fun findAccount(accountId: Long): Account {
        val account: Optional<Account>  = accountRepository.findById(accountId)

        if (account.isEmpty) {
            throw AccountDontExistsException(messageExceptionAccountDontExists, descriptionExceptionAccountDontExistsException)
        }
        return account.get()
    }

    override fun debitAccount(accountId: Long, valueOfDebit: Double) {
        TODO("Not yet implemented")
    }
}
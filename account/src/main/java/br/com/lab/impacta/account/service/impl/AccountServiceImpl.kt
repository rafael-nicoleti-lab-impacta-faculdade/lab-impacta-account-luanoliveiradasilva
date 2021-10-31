package br.com.lab.impacta.account.service.impl

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanceException
import br.com.lab.impacta.account.model.Account
import br.com.lab.impacta.account.repository.AccountRepository
import br.com.lab.impacta.account.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl : AccountService {

    @Autowired
    lateinit var accountRepository: AccountRepository
    @Value("\${lab.account.exceptions.account-dont-exists-message}")
    private lateinit var messageExceptionAccountDontExists: String

    @Value("\${lab.account.exception.account-dont-exists-description}")
    private lateinit var descriptionExceptionAccountDontExistsException: String

    @Value("\${lab.account.exceptions.account-without-balance-message}")
    private lateinit var messageExceptionAccountWithoutBalance: String

    @Value("\${lab.account.exceptions.account-without-balance-description}")
    private lateinit var descriptionExceptionAccountWithoutBalance: String


    override fun findAccount(accountId: Long): Account {
        val account: Optional<Account>  = accountRepository.findById(accountId)

        if (account.isEmpty) {
            throw AccountDontExistsException(messageExceptionAccountDontExists, descriptionExceptionAccountDontExistsException)
        }
        return account.get()
    }

    override fun debitAccount(accountId: Long, valueOfDebit: Double) {
       val account: Account = this.findAccount(accountId)

        val debited: Boolean = account.debit(valueOfDebit)

        if(!debited)
                throw AccountWithoutBalanceException(messageExceptionAccountWithoutBalance, descriptionExceptionAccountWithoutBalance)

        accountRepository.save(account);
    }
}
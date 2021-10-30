package br.com.lab.impacta.account.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterAll
import kotlin.test.assertFalse


internal class AccountTest {

    private var conta: Account = Account(1,123,100.0,Person(1,"joao","123455433"))



    @Test
    fun testar_debit_true() {
        assert(conta.debit(10.0))
    }

    @Test
    fun testar_debit_false() {
        assertFalse(conta.debit(120.0))
    }
}
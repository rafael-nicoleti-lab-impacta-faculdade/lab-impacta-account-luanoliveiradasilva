package br.com.lab.impacta.account.handler.exception

import sun.jvm.hotspot.oops.CellTypeState.value


class AccountWithoutBalanceException(override val message: String,
                                     val description: String): RuntimeException(message) {
}
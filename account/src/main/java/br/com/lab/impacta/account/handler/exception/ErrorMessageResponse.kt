package br.com.lab.impacta.account.handler.exception

import java.util.*

class ErrorMessageResponse (
    val statusCode: Int,
    val timestamp : Date,
    val message: String,
    val description : String
        ){


}
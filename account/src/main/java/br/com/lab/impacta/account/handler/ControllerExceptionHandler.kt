package br.com.lab.impacta.account.handler


import br.com.lab.impacta.account.handler.exception.AccountDontExistsException
import br.com.lab.impacta.account.handler.exception.ErrorMessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class ControllerExceptionHandler {


    @ExceptionHandler(AccountDontExistsException::class)
    fun accountNotFoundException (accountDontExistsException: AccountDontExistsException,
                                  webRequest: WebRequest): ResponseEntity<ErrorMessageResponse>{

        val errorMessageResponse = ErrorMessageResponse(HttpStatus.NOT_FOUND.value(), Date(),
            accountDontExistsException.message,
            accountDontExistsException.description
        )

        return ResponseEntity(errorMessageResponse, HttpStatus.NOT_FOUND)
    }
}
package br.com.lab.impacta.account.handler


import br.com.lab.impacta.account.handler.exception.AccountDontExistsException
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanceException
import br.com.lab.impacta.account.handler.exception.ErrorMessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@ControllerAdvice
class ControllerExceptionHandler {


    @ExceptionHandler(AccountDontExistsException::class)
    fun accountNotFoundException (accountDontExistsException: AccountDontExistsException): ResponseEntity<ErrorMessageResponse>{

        val errorMessageResponse = ErrorMessageResponse(HttpStatus.NOT_FOUND.value(),
            Date(),
            accountDontExistsException.message,
            accountDontExistsException.description
        )

        return ResponseEntity(errorMessageResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AccountWithoutBalanceException::class)
    fun WithoutBalanceException (accountWithoutBalanceException : AccountWithoutBalanceException): ResponseEntity<ErrorMessageResponse>{

        val message = ErrorMessageResponse(HttpStatus.BAD_REQUEST.value(),
            Date(),
            accountWithoutBalanceException.message,
            accountWithoutBalanceException.description)

            return ResponseEntity(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RuntimeException::class)
    fun errorGeneral (runExceptio: RuntimeException): ResponseEntity<ErrorMessageResponse>{

        val message = runExceptio.message?.let {
            ErrorMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Date(),
                it,
                "We were unable to process your request."
            )
        }

        return ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
package br.com.marceloazevedo.dentist.odonto.api.handler

import br.com.marceloazevedo.dentist.odonto.api.exchange.response.ErrorResponse
import br.com.marceloazevedo.dentist.odonto.api.exchange.response.FieldError
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.apache.logging.log4j.LogManager
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    private val logger = LogManager.getLogger(ApiExceptionHandler::class.java)

    @Autowired
    private lateinit var messageSource: MessageSource

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("m=handleMethodArgumentNotValid")
        val fieldsError = ex.fieldErrors.map { reference ->
            FieldError(reference.field, reference.defaultMessage ?: "message not found", reference.rejectedValue)
        }
        return ResponseEntity(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                errors = fieldsError, path = (request as ServletWebRequest).request.requestURI ?: "not found",
                error = messageSource.getMessage("object.fields.notNull", null, Locale.ENGLISH)),
                HttpStatus.BAD_REQUEST)
    }

    override fun handleMissingPathVariable(ex: MissingPathVariableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("m=handleMissingPathVariable")
        return super.handleMissingPathVariable(ex, headers, status, request)
    }

    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("m=handleNoHandlerFoundException")
        return super.handleNoHandlerFoundException(ex, headers, status, request)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("m=handleHttpMessageNotReadable")
        if (ex.cause is MissingKotlinParameterException) return handleMissingKotlinParameter(request, ex.cause as MissingKotlinParameterException) as ResponseEntity<Any>
        return super.handleHttpMessageNotReadable(ex, headers, status, request)
    }

    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    fun handleMissingKotlinParameter(request: WebRequest, exception: MissingKotlinParameterException): ResponseEntity<ErrorResponse> {
        logger.info("m=handleMissingKotlinParameter")
        val fieldsError = exception.path.map { reference ->
            FieldError(reference.fieldName, messageSource.getMessage("field.notNull", null, Locale.ENGLISH), null)
        }
        return ResponseEntity(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                errors = fieldsError, path = (request as ServletWebRequest).request.requestURI ?: "not found",
                error = messageSource.getMessage("object.fields.notNull", null, Locale.ENGLISH)),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(request: WebRequest, exception: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        logger.info("m=handleConstraintViolationException")
        return ResponseEntity(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                errors = listOf(),
                path = "",
                error = messageSource.getMessage("object.fields.constraintValidationError", null, Locale.ENGLISH)),
                HttpStatus.BAD_REQUEST)
    }


}
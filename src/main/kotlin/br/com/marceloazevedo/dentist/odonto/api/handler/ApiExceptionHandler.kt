package br.com.marceloazevedo.dentist.odonto.api.handler

import br.com.marceloazevedo.dentist.odonto.api.exception.GenreNotValidException
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.response.ErrorResponse
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.response.FieldError
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.apache.logging.log4j.LogManager
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

@Suppress("unused")
@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    private val logger = LogManager.getLogger(ApiExceptionHandler::class.java)

    @Autowired
    private lateinit var messageSource: MessageSource

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.error("method=handleMethodArgumentNotValid", ex)
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
        logger.info("method=handleMissingPathVariable")
        return super.handleMissingPathVariable(ex, headers, status, request)
    }

    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("method=handleNoHandlerFoundException")
        return super.handleNoHandlerFoundException(ex, headers, status, request)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        logger.info("method=handleHttpMessageNotReadable")
        if (ex.cause is MissingKotlinParameterException) return handleMissingKotlinParameter(request, ex.cause as MissingKotlinParameterException) as ResponseEntity<Any>
        if (ex.cause is ValueInstantiationException) {
            val instantiationExceptionCause = (ex.cause as ValueInstantiationException).cause
            val fieldName: String? = (ex.cause as JsonMappingException).path.firstOrNull()?.fieldName
            if (instantiationExceptionCause is GenreNotValidException) {
                val genreNotValidException = GenreNotValidException(instantiationExceptionCause.message, instantiationExceptionCause.value, fieldName)
                return handleGenreNotValidException(request, genreNotValidException) as ResponseEntity<Any>
            }
        }
        return super.handleHttpMessageNotReadable(ex, headers, status, request)
    }

    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    fun handleMissingKotlinParameter(request: WebRequest, exception: MissingKotlinParameterException): ResponseEntity<ErrorResponse> {
        logger.error("method=handleMissingKotlinParameter", exception)
        val fieldsError = exception.path.map { reference ->
            FieldError(reference.fieldName, messageSource.getMessage("field.notNull", null, Locale.ENGLISH), null)
        }
        return ResponseEntity(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                errors = fieldsError, path = (request as ServletWebRequest).request.requestURI ?: "not found",
                error = messageSource.getMessage("object.fields.notNull", null, Locale.ENGLISH)),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [GenreNotValidException::class])
    fun handleGenreNotValidException(request: WebRequest, exception: GenreNotValidException): ResponseEntity<ErrorResponse> {
        logger.error("method=handleGenreNotValidException", exception)
        val fieldsError = listOf(FieldError(
                exception.fieldPath ?: "", exception.message, exception.value
        ))

        return ResponseEntity(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                errors = fieldsError, path = (request as ServletWebRequest).request.requestURI ?: "not found",
                error = messageSource.getMessage("object.fields.constraintValidationError", null, Locale.ENGLISH)),
                HttpStatus.BAD_REQUEST)
    }

}

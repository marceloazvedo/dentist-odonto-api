package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.response

import java.time.LocalDateTime

data class ErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String?,
        val path: String,
        val errors: List<FieldError>?)

data class FieldError(val field: String, val message: String, val value: Any?)
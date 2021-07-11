package br.com.marceloazevedo.dentist.odonto.api.exception

import java.lang.RuntimeException

class GenreNotValidException(override val message: String, val value: String, val fieldPath: String? = null): RuntimeException()

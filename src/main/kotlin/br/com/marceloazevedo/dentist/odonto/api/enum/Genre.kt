package br.com.marceloazevedo.dentist.odonto.api.enum

import br.com.marceloazevedo.dentist.odonto.api.exception.GenreNotValidException
import com.fasterxml.jackson.annotation.JsonCreator

enum class Genre {

    MALE, FEMALE, OTHER;

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String?): Genre {
            if (value.isNullOrBlank()) throw GenreNotValidException("The genre \"$value\" is not valid", value ?: "")
            return values().firstOrNull { it.name == value }
                    ?: throw GenreNotValidException("The genre \"$value\" is not valid", value)
        }
    }

}
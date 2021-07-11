package br.com.marceloazevedo.dentist.odonto.api.enum

import br.com.marceloazevedo.dentist.odonto.api.exception.GenreNotValidException
import com.fasterxml.jackson.annotation.JsonCreator

enum class UF(name: String) {

    RO("Rondônia"),
    AC("Acre"),
    AM("Amazonas"),
    RR("Roraima"),
    PA("Pará"),
    AP("Amapá"),
    TO("Tocantins"),
    MA("Maranhão"),
    PI("Piauí"),
    CE("Ceará"),
    RN("Rio Grande do Norte"),
    PB("Paraíba"),
    PE("Pernambuco"),
    AL("Alagoas"),
    SE("Sergipe"),
    BA("Bahia"),
    MG("Minas Gerais"),
    ES("Espírito Santo"),
    RJ("Rio de Janeiro"),
    SP("São Paulo"),
    PR("Paraná"),
    SC("Santa Catarina"),
    RS("Rio Grande do Sul"),
    MS("Mato Grosso do Sul"),
    MT("Mato Grosso"),
    GO("Goiás"),
    DF("Distrito Federal");

    companion object {
        @JsonCreator
        @JvmStatic
        fun create(value: String?): UF {
            if (value.isNullOrBlank()) throw GenreNotValidException("The UF \"$value\" is not valid", value ?: "")
            return values().firstOrNull { it.name == value }
                ?: throw GenreNotValidException("The UF \"$value\" is not valid", value)
        }
    }


}
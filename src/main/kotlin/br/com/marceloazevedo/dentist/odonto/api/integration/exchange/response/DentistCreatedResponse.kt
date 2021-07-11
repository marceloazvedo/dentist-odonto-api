package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.response

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.model.Address
import br.com.marceloazevedo.dentist.odonto.api.model.CRO
import br.com.marceloazevedo.dentist.odonto.api.model.Contact
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.util.parserToString
import java.time.LocalDateTime

data class DentistCreatedResponse(
        val id: String,
        val cro: CRO,
        val name: String,
        val cpf: String,
        val rg: String,
        val genre: Genre,
        val birthDate: String,
        val contacts: List<Contact>,
        val addresses: List<Address>?,
        val createdAt: LocalDateTime
) {
    constructor(dentist: Dentist) : this(
            dentist.id, dentist.cro, dentist.name, dentist.cpf, dentist.rg, dentist.genre,
            dentist.birthDate.parserToString(), dentist.contacts, dentist.addresses, dentist.createdAt
    )
}
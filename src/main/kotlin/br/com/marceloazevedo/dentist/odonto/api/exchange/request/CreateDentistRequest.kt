package br.com.marceloazevedo.dentist.odonto.api.exchange.request

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateDentistRequest(
        @field:NotNull
        val cro: CRORequest?,
        @field:NotEmpty @field:NotNull
        val name: String?,
        @field:NotEmpty @field:NotNull
        val cpf: String?,
        @field:NotEmpty @field:NotNull
        val rg: String?,
        @field:NotNull
        val genre: Genre?,
        @field:NotEmpty @field:NotNull
        val birthDate: String,
        @field:NotEmpty
        val contacts: List<ContactRequest>,
        val address: List<AddressRequest>
)

data class CRORequest(
        @field:NotEmpty @field:NotNull
        val number: String?,
        @field:NotEmpty @field:NotNull
        val uf: UF?,
)

data class ContactRequest(
        @field:NotEmpty @field:NotNull
        val name: String?,
        @field:NotEmpty @field:NotNull
        val value: String?,
        @field:NotEmpty @field:NotNull
        val type: ContactType?
)

data class AddressRequest(
        @field:NotEmpty @field:NotNull
        val cep: String?,
        @field:NotEmpty @field:NotNull
        val street: String?,
        @field:NotEmpty @field:NotNull
        val number: String?,
        @field:NotEmpty @field:NotNull
        val city: String,
        @field:NotEmpty @field:NotNull
        val uf: UF,
        val complement: String
)

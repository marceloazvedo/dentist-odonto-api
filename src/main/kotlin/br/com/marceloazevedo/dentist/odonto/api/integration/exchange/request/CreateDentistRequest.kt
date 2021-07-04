package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.validation.CpfValid
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateDentistRequest(
        @field:Valid @field:NotNull
        val cro: CRORequest?,
        @field:NotBlank
        val name: String?,
        @field:NotBlank @field:CpfValid
        val cpf: String?,
        @field:NotBlank
        val rg: String?,
        @field:NotNull
        val genre: Genre?,
        @field:NotBlank
        val birthDate: String?,
        @field:Valid @field:NotEmpty
        val contacts: List<ContactRequest?>?,
        @field:Valid
        val addresses: List<AddressRequest?>?
)

data class CRORequest(
        @field:NotBlank
        val number: String?,
        @field:NotNull
        val uf: UF?,
)

data class ContactRequest(
        @field:NotBlank
        val name: String?,
        @field:NotBlank
        val value: String?,
        @field:NotNull
        val type: ContactType?
)

data class AddressRequest(
        @field:NotBlank
        val cep: String?,
        @field:NotBlank
        val street: String?,
        @field:NotBlank
        val number: String?,
        @field:NotBlank
        val city: String?,
        @field:NotNull
        val uf: UF?,
        val complement: String?
)

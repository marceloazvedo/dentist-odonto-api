package br.com.marceloazevedo.dentist.odonto.api.integration.faker

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.AddressRequest
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.CRORequest
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.ContactRequest
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.CreateDentistRequest
import br.com.marceloazevedo.dentist.odonto.api.model.Address
import br.com.marceloazevedo.dentist.odonto.api.model.CRO
import br.com.marceloazevedo.dentist.odonto.api.model.Contact
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun phoneNumberContact() =
        Contact(
                name = "Personal Number",
                type = ContactType.PHONE_NUMBER,
                value = "839874578589"
        )

fun emailContact() =
        Contact(
                name = "Personal Email",
                type = ContactType.EMAIL,
                value = "teste@company.com"
        )

fun validCPF() = "77882942090"

fun invalidCPF() = "12312312132"

fun birthDate(): LocalDate = LocalDate.parse("12-04-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy"))

fun cro() = CRO(UF.PB, "5412")

fun address() =
        Address(
                cep = "59350000",
                street = "Test Street",
                number = "0",
                city = "Santana do Seridó",
                uf = UF.RN
        )

fun generateFullDentist() = Dentist(
        cro = cro(),
        name = "Test de Create a Person",
        addresses = listOf(address()),
        contacts = listOf(phoneNumberContact()),
        birthDate = birthDate(),
        cpf = validCPF(),
        genre = Genre.MALE,
        rg = "123312132123"
)

fun generateFullDentistRequest() = CreateDentistRequest(
        addresses = listOf(AddressRequest(
                cep = "59350000",
                city = "Santana do Test",
                uf = UF.RN,
                number = "0",
                street = "Rua dos Testes",
                complement = null
        )),
        cro = CRORequest("1234", UF.RN),
        birthDate = "14/06/1990",
        genre = Genre.MALE,
        rg = "2848652",
        cpf = "10488026040",
        name = "Dentist's Test",
        contacts = listOf(ContactRequest(
                name = "Telefone pessoal",
                type = ContactType.WHATSAPP,
                value = "83987457858"
        ))
)

package br.com.marceloazevedo.dentist.odonto.api.faker

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import br.com.marceloazevedo.dentist.odonto.api.model.Address
import br.com.marceloazevedo.dentist.odonto.api.model.CRO
import br.com.marceloazevedo.dentist.odonto.api.model.Contact
import java.text.SimpleDateFormat
import java.util.*

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

fun birthDate(): Date {
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.parse("12-04-1995")
}

fun cro() = CRO(UF.PB, "5412")

fun address() =
        Address(
                cep = "59350000",
                street = "Test Street",
                number = "0",
                city = "Santana do Seridó",
                uf = UF.RN
        )

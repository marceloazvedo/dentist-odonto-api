package br.com.marceloazevedo.dentist.odonto.api.service

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import br.com.marceloazevedo.dentist.odonto.api.faker.*
import br.com.marceloazevedo.dentist.odonto.api.model.CRO
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.repository.DentistRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class DentistServiceTest {

    @Autowired
    private lateinit var dentistService: DentistService
    @Autowired
    private lateinit var dentistRepository: DentistRepository

    @BeforeEach
    fun clearDatabase() {
        dentistRepository.deleteAll()
    }

    @Test
    fun `should save a dentist without a address with success`() {
        val dentist = dentistService.save(Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(address()),
                contacts = listOf(phoneNumberContact()),
                birthDate = birthDate(),
                cpf = validCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 0)
        assertNotNull(dentist.createdAt)
        assertNotNull(dentist.id)
    }

    @Test
    fun `should save a dentist with a address with success`() {
        val dentist = dentistService.save(Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(),
                contacts = listOf(phoneNumberContact()),
                birthDate = birthDate(),
                cpf = validCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 1)
        assertNotNull(dentist.createdAt)
        assertNotNull(dentist.id)
    }

    @Test
    fun `should not save a dentist with invalid cpf`() {
        val dentist = dentistService.save(Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(),
                contacts = listOf(phoneNumberContact()),
                birthDate = birthDate(),
                cpf = invalidCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 0)
    }


    @Test
    fun `should not save a dentist if the contact have just a email`() {
        dentistService.save(Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(),
                contacts = listOf(emailContact()),
                birthDate = birthDate(),
                cpf = invalidCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 0)
    }

    @Test
    fun `should not save a dentist without no one contact`() {
        dentistService.save(Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(),
                contacts = listOf(),
                birthDate = birthDate(),
                cpf = invalidCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 0)
    }

    @Test
    fun `should not save a dentist with CRO blank`() {
        dentistService.save(Dentist(
                cro = CRO(UF.PB, ""),
                name = "Test de Create a Person",
                address = listOf(),
                contacts = listOf(),
                birthDate = birthDate(),
                cpf = invalidCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(dentists.size, 0)
    }

}
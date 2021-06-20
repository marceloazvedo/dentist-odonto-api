package br.com.marceloazevedo.dentist.odonto.api.repository

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.faker.*
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/**
 * To run this tests, needs to run a local Amazon DynamoDB in port 8000.
 */
@ActiveProfiles("test")
@SpringBootTest
internal class DentistRepositoryTest {

    @Autowired
    private lateinit var dentistRepository: DentistRepository

    @BeforeEach
    fun clearDatabase() {
        dentistRepository.deleteAll()
    }

    @Test
    fun `should save a dentist with success`() {
        val dentistToSave = Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(address()),
                contacts = listOf(phoneNumberContact()),
                birthDate = birthDate(),
                cpf = validCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        )

        val dentistSaved = dentistRepository.save(dentistToSave)

        val dentistsQuantity = dentistRepository.count()
        assertNotNull(dentistToSave.id)
        assertNotNull(dentistToSave.createdAt)
        assertNull(dentistToSave.updatedAt)
        assertEquals(1, dentistsQuantity)
        assertEquals(dentistSaved.name, dentistToSave.name)
        assertEquals(dentistSaved.birthDate, dentistToSave.birthDate)
        assertEquals(dentistSaved.cpf, dentistToSave.cpf)
        assertEquals(dentistSaved.rg, dentistToSave.rg)
        assertEquals(dentistSaved.genre, Genre.MALE)
        assertEquals(dentistSaved.address.size, 1)
        assertEquals(dentistSaved.contacts.size, 1)
        assertNotNull(dentistSaved.createdAt)
        assertNotNull(dentistSaved.id)
    }

}

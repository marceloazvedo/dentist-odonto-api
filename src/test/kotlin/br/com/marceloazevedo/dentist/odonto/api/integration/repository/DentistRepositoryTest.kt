package br.com.marceloazevedo.dentist.odonto.api.integration.repository

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentist
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
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
        val dentistToSave = generateFullDentist()

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
        assertEquals(dentistSaved.addresses!!.size, 1)
        assertEquals(dentistSaved.contacts.size, 1)
        assertNotNull(dentistSaved.createdAt)
        assertNotNull(dentistSaved.id)
    }

}

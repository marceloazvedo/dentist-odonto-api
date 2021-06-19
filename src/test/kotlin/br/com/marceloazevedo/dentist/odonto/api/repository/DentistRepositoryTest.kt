package br.com.marceloazevedo.dentist.odonto.api.repository

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.faker.*
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@ActiveProfiles("test")
@DataJpaTest
internal class DentistRepositoryTest {

    @Autowired
    private lateinit var dentistRepository: DentistRepository

    @Autowired
    private lateinit var entityManager: EntityManager

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

        val dentistBeforeSave = dentistToSave.copy()

        val dentistSaved = dentistRepository.saveAndFlush(dentistToSave)
        entityManager.detach(dentistToSave)

        val dentistsQuantity = dentistRepository.count()
        assertNull(dentistBeforeSave.id)
        assertNotNull(dentistBeforeSave.createdAt)
        assertNull(dentistBeforeSave.updatedAt)
        assertEquals(1, dentistsQuantity)
        assertEquals(dentistSaved.name, dentistBeforeSave.name)
        assertEquals(dentistSaved.birthDate, dentistBeforeSave.birthDate)
        assertEquals(dentistSaved.cpf, dentistBeforeSave.cpf)
        assertEquals(dentistSaved.rg, dentistBeforeSave.rg)
        assertEquals(dentistSaved.genre, Genre.MALE)
        assertEquals(dentistSaved.address.size, 1)
        assertEquals(dentistSaved.contacts.size, 1)
        assertNotNull(dentistSaved.createdAt)
        assertNotNull(dentistSaved.id)
    }

}
package br.com.marceloazevedo.dentist.odonto.api.service

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.faker.*
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.repository.DentistRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
internal class DentistServiceTest {

    @InjectMockKs
    private lateinit var dentistService: DentistService
    @MockK
    private lateinit var dentistRepository: DentistRepository

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `should save a dentist with success`() {
        val dentistToCreate = Dentist(
                cro = cro(),
                name = "Test de Create a Person",
                address = listOf(address()),
                contacts = listOf(phoneNumberContact()),
                birthDate = birthDate(),
                cpf = validCPF(),
                genre = Genre.MALE,
                rg = "123312132123"
        )

        val dentistSaved = dentistToCreate.copy(id = "test")

        every { dentistRepository.save(any()) } returns dentistSaved

        dentistService.create(dentistToCreate)

        verify(exactly = 1) { dentistRepository.save(dentistToCreate) }
    }

}
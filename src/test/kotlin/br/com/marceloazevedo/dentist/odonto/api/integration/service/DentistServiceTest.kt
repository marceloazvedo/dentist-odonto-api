package br.com.marceloazevedo.dentist.odonto.api.integration.service

import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentist
import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentistRequest
import br.com.marceloazevedo.dentist.odonto.api.integration.repository.DentistRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    fun `should save a dentist with all field filled with success`() {
        every { dentistRepository.save(any()) } returns generateFullDentist()

        dentistService.create(generateFullDentistRequest())

        verify(exactly = 1) { dentistRepository.save(any()) }
    }

    @Disabled
    @Test
    fun `should not save a dentist with a invalid cpf`() {
        every { dentistRepository.save(any()) } returns generateFullDentist()

        val createDentistRequest = generateFullDentistRequest().copy(cpf = "23123123321")

        assertThrows<Exception> { dentistService.create(createDentistRequest) }

        verify(exactly = 0) { dentistRepository.save(any()) }
    }

    @Disabled
    @Test
    fun `should not save a dentist with a blank cpf`() {
        every { dentistRepository.save(any()) } returns generateFullDentist()

        val createDentistRequest = generateFullDentistRequest().copy(cpf = "")

        assertThrows<Exception> { dentistService.create(createDentistRequest) }

        verify(exactly = 0) { dentistRepository.save(any()) }
    }

}
package br.com.marceloazevedo.dentist.odonto.api.integration.service

import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentist
import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentistRequest
import br.com.marceloazevedo.dentist.odonto.api.integration.repository.DentistRepository
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.util.parserToString
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
        val dentistRequest = generateFullDentistRequest()
        val dentist = Dentist(dentistRequest)

        every { dentistRepository.save(any()) } returns dentist


        val dentistCreated = dentistService.create(dentistRequest)

        val addressCreated = dentistCreated.addresses?.first()
        val contactCreated = dentistCreated.contacts.first()

        assertNotNull(addressCreated?.id)
        assertEquals(addressCreated?.city, dentistRequest.addresses?.first()?.city)
        assertEquals(addressCreated?.zipCode, dentistRequest.addresses?.first()?.zipCode)
        assertEquals(addressCreated?.complement, dentistRequest.addresses?.first()?.complement)
        assertEquals(addressCreated?.number, dentistRequest.addresses?.first()?.number)
        assertEquals(addressCreated?.street, dentistRequest.addresses?.first()?.street)
        assertEquals(addressCreated?.uf, dentistRequest.addresses?.first()?.uf)

        assertNotNull(contactCreated.id)
        assertEquals(contactCreated.name, dentistRequest.contacts?.first()?.name)
        assertEquals(contactCreated.type, dentistRequest.contacts?.first()?.type)
        assertEquals(contactCreated.value, dentistRequest.contacts?.first()?.value)

        assertEquals(dentistCreated.cro.number, dentistRequest.cro?.number)
        assertEquals(dentistCreated.cro.uf, dentistRequest.cro?.uf)

        assertEquals(dentistCreated.name, dentistRequest.name)
        assertEquals(dentistCreated.birthDate.parserToString(), dentistRequest.birthDate)
        assertEquals(dentistCreated.cpf, dentistRequest.cpf)
        assertEquals(dentistCreated.genre, dentistRequest.genre)
        assertEquals(dentistCreated.rg, dentistRequest.rg)

        verify(exactly = 1) { dentistRepository.save(any()) }
    }

}
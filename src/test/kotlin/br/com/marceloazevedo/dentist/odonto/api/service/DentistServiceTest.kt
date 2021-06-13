package br.com.marceloazevedo.dentist.odonto.api.service

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.repository.DentistRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat

@SpringBootTest
internal class DentistServiceTest {

    @Autowired
    private lateinit var dentistService: DentistService
    @Autowired
    private lateinit var dentistRepository: DentistRepository

    @Test
    fun `should save a dentist with success`() {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val birthDate = formatter.parse("12-04-1995")

        val dentist = dentistService.save(Dentist(
                cro = "12312331212",
                name = "Test de Cadastro Pessoa",
                address = listOf(),
                contacts = listOf(),
                birthDate = birthDate,
                cpf = "10662165411",
                genre = Genre.MALE,
                rg = "123312132123"
        ))

        val dentists = dentistRepository.findAll()
        assertEquals(1, dentists.size)
    }
}
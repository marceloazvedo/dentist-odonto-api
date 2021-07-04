package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.controller

import br.com.marceloazevedo.dentist.odonto.api.integration.faker.generateFullDentist
import br.com.marceloazevedo.dentist.odonto.api.integration.service.DentistService
import br.com.marceloazevedo.dentist.odonto.api.util.parserToString
import com.fasterxml.jackson.databind.ObjectMapper
import org.flywaydb.core.internal.util.FileCopyUtils
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.IOException
import java.io.InputStreamReader
import java.io.UncheckedIOException
import java.nio.charset.StandardCharsets

@ActiveProfiles("test")
@WebMvcTest(DentistController::class)
class DentistControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var service: DentistService

    @Test
    fun `should save a dentist with success`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(successJsonRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())

                    .andExpect(status().isCreated)
                    .andExpect(jsonPath("$.id", `is`(dentist.id)))
                    .andExpect(jsonPath("$.cro.uf", `is`(dentist.cro.uf.name)))
                    .andExpect(jsonPath("$.cro.number", `is`(dentist.cro.number)))
                    .andExpect(jsonPath("$.name", `is`(dentist.name)))
                    .andExpect(jsonPath("$.cpf", `is`(dentist.cpf)))
                    .andExpect(jsonPath("$.rg", `is`(dentist.rg)))
                    .andExpect(jsonPath("$.genre", `is`(dentist.genre.name)))
                    .andExpect(jsonPath("$.birth_date", `is`(dentist.birthDate.parserToString())))
                    .andExpect(jsonPath("$.contacts[0].id", `is`(dentist.contacts.first().id)))
                    .andExpect(jsonPath("$.contacts[0].name", `is`(dentist.contacts.first().name)))
                    .andExpect(jsonPath("$.contacts[0].value", `is`(dentist.contacts.first().value)))
                    .andExpect(jsonPath("$.contacts[0].type", `is`(dentist.contacts.first().type.name)))
                    .andExpect(jsonPath("$.addresses[0].id", `is`(dentist.addresses?.first()?.id)))
                    .andExpect(jsonPath("$.addresses[0].cep", `is`(dentist.addresses?.first()?.cep)))
                    .andExpect(jsonPath("$.addresses[0].street", `is`(dentist.addresses?.first()?.street)))
                    .andExpect(jsonPath("$.addresses[0].number", `is`(dentist.addresses?.first()?.number)))
                    .andExpect(jsonPath("$.addresses[0].city", `is`(dentist.addresses?.first()?.city)))
                    .andExpect(jsonPath("$.addresses[0].uf", `is`(dentist.addresses?.first()?.uf?.name)))
                    .andExpect(jsonPath("$.addresses[0].created_at", `is`(dentist.addresses?.first()?.createdAt.toString())))
        }
        verify(service, times(1)).create(any())
    }

    @Test
    fun `should not save a dentist with a invalid cpf`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestCpfNotValid))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("The fields in object cannot be null")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("cpf")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("your cpf is invalid")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("12312312311")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Test
    fun `should not save a dentist with a blank cpf`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestBlankCpf))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("The fields in object cannot be null")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("cpf")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("must not be blank")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Test
    fun `should not save a dentist with a genre blank`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestBlankGenre))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("This object has invalid fields")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("genre")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("The genre \"\" is not valid")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Test
    fun `should not save a dentist with a invalid genre`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestInvalidGenre))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("This object has invalid fields")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("genre")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("The genre \"AAA\" is not valid")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("AAA")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Test
    fun `should not save a dentist with a birth_date blank`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestBirthDateBlank))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("The fields in object cannot be null")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("birth_date")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("must not be blank")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Test
    fun `should not save a dentist with a birth_date invalid`() {
        val dentist = generateFullDentist()

        `when`(service.create(any())).thenReturn(dentist)

        with(mvc) {
            perform(post("/dentist")
                    .content(asString(badRequestBirthDateInvalid))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$.timestamp").isNotEmpty)
                    .andExpect(jsonPath("$.status", `is`(400)))
                    .andExpect(jsonPath("$.error", `is`("The fields in object cannot be null")))
                    .andExpect(jsonPath("$.path", `is`("/dentist")))
                    .andExpect(jsonPath("$.errors").isArray)
                    .andExpect(jsonPath("$.errors[0].field", `is`("birth_date")))
                    .andExpect(jsonPath("$.errors[0].message", `is`("The value informed to this date is invalid")))
                    .andExpect(jsonPath("$.errors[0].value", `is`("12/44/200X")))
                    .andExpect(jsonPath("$.errors[1]").doesNotExist())
        }
        verify(service, times(0)).create(any())
    }

    @Value("classpath:json/request/success_request.json")
    private lateinit var successJsonRequest: Resource

    @Value("classpath:json/request/bad_request_cpf_not_valid.json")
    private lateinit var badRequestCpfNotValid: Resource

    @Value("classpath:json/request/bad_request_cpf_blank.json")
    private lateinit var badRequestBlankCpf: Resource

    @Value("classpath:json/request/bad_request_genre_blank.json")
    private lateinit var badRequestBlankGenre: Resource

    @Value("classpath:json/request/bad_request_genre_invalid.json")
    private lateinit var badRequestInvalidGenre: Resource

    @Value("classpath:json/request/bad_request_birth_date_blank.json")
    private lateinit var badRequestBirthDateBlank: Resource

    @Value("classpath:json/request/bad_request_birth_date_invalid.json")
    private lateinit var badRequestBirthDateInvalid: Resource

}

fun asString(resource: Resource): String {
    try {
        InputStreamReader(resource.inputStream, StandardCharsets.UTF_8).use { reader -> return FileCopyUtils.copyToString(reader) }
    } catch (e: IOException) {
        throw UncheckedIOException(e)
    }
}

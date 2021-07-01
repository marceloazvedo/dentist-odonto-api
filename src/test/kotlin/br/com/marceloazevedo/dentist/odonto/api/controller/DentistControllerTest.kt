package br.com.marceloazevedo.dentist.odonto.api.controller

import br.com.marceloazevedo.dentist.odonto.api.service.DentistService
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@ActiveProfiles("test")
@WebMvcTest(DentistController::class)
class DentistControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var service: DentistService

    @Test
    fun `should not save a dentist with success`() {
        with(mvc) {
            perform(post("/dentist").content(
                    """
                        {
                            "name": "Dentist Test"                        
                        }
                    """.trimIndent()
            )
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$", `is`("test"), String::class.java))
        }
    }

    @Test
    fun `should not save a dentist without a invalid cpf`() {
        with(mvc) {
            perform(post("/dentist").content(
                    """
                        {
                            "name": "Dentist Test"                        
                        }
                    """.trimIndent()
            )
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest)
                    .andExpect(jsonPath("$", `is`("test"), String::class.java))
        }
    }

}
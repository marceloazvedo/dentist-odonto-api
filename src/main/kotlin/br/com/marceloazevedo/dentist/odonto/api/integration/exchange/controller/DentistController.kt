package br.com.marceloazevedo.dentist.odonto.api.integration.exchange.controller

import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.CreateDentistRequest
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.response.DentistCreatedResponse
import br.com.marceloazevedo.dentist.odonto.api.integration.service.DentistService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Suppress("unused")
@RestController
@RequestMapping("/dentist")
class DentistController {

    private val logger: Logger = LogManager.getLogger(this::class.java)

    @Autowired
    private lateinit var dentistService: DentistService

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody createDentist: CreateDentistRequest): ResponseEntity<DentistCreatedResponse> {
        logger.info("method=create")

        val client = dentistService.create(createDentist)

        return ResponseEntity(DentistCreatedResponse(client), HttpStatus.CREATED)
    }

}

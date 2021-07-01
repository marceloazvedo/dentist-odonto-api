package br.com.marceloazevedo.dentist.odonto.api.controller

import br.com.marceloazevedo.dentist.odonto.api.exchange.request.CreateDentistRequest
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/dentist")
class DentistController {

    private val logger: Logger = LogManager.getLogger(this::class.java)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody createDentist: CreateDentistRequest): ResponseEntity<String> {
        logger.info("method=create")
        return ResponseEntity.ok().build()
    }

}

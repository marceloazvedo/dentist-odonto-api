package br.com.marceloazevedo.dentist.odonto.api.integration.service

import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.CreateDentistRequest
import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.integration.repository.DentistRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DentistService {

    private val logger: Logger = LogManager.getLogger(this::class.java)

    @Autowired
    private lateinit var dentistRepository: DentistRepository

    fun create(createDentist: CreateDentistRequest): Dentist {
        logger.info("method=create")
        return dentistRepository.save(Dentist(createDentist))
    }
}
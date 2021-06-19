package br.com.marceloazevedo.dentist.odonto.api.service

import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import br.com.marceloazevedo.dentist.odonto.api.repository.DentistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DentistService {

    @Autowired
    private lateinit var dentistRepository: DentistRepository

    fun create(dentist: Dentist): Dentist = dentistRepository.save(dentist)
}
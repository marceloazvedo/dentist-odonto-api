package br.com.marceloazevedo.dentist.odonto.api.service

import br.com.marceloazevedo.dentist.odonto.api.model.Dentist

interface DentistService {

    fun save(dentist: Dentist): Dentist

}
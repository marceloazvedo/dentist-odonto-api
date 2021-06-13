package br.com.marceloazevedo.dentist.odonto.api.repository

import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import org.springframework.data.jpa.repository.JpaRepository

interface DentistRepository: JpaRepository<Dentist, Long> {
}
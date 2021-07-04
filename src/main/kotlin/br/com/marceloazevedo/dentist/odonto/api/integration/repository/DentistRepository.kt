package br.com.marceloazevedo.dentist.odonto.api.integration.repository

import br.com.marceloazevedo.dentist.odonto.api.model.Dentist
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface DentistRepository: CrudRepository<Dentist, Long>

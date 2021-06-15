package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class CRO(
        @Enumerated(EnumType.STRING)
        val uf: UF,
        val number: String
)

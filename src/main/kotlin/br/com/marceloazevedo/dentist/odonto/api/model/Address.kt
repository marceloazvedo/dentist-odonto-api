package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Address(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val cep: String,
        val street: String,
        val number: String,
        val city: String,
        val uf: UF,
        val complement: String? = null,
        @Temporal(TemporalType.TIMESTAMP) @CreatedDate
        val createdAt: Date = Date.from(Instant.now())
)

package br.com.marceloazevedo.dentist.odonto.api.model

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Address(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val cep: String,
        val street: String,
        val number: String,
        val city: String,
        val complement: String?,
        @Temporal(TemporalType.TIMESTAMP) @CreatedDate
        val createdAt: Date = Date.from(Instant.now())
)

package br.com.marceloazevedo.dentist.odonto.api.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
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
        val createdAt: LocalDateTime = LocalDateTime.now()
)

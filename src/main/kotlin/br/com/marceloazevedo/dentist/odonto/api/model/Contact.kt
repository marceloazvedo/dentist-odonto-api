package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Contact(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        val value: String,
        val type: ContactType,
        @Temporal(TemporalType.TIMESTAMP) @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now()
)

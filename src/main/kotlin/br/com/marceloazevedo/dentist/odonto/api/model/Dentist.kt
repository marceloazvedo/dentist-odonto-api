package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Dentist(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val cro: String,
        val name: String,
        val cpf: String,
        val rg: String?,
        val genre: Genre,
        @Temporal(TemporalType.DATE)
        val birthDate: LocalDateTime,
        @OneToMany(cascade = [CascadeType.ALL])
        val contacts: List<Contact>,
        @OneToMany(cascade = [CascadeType.ALL])
        val address: List<Address>,
        @Temporal(TemporalType.TIMESTAMP) @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @LastModifiedDate
        val updatedAt: LocalDateTime?
)

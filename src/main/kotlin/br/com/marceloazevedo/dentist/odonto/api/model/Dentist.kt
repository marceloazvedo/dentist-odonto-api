package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.Genre
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Dentist(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "uf", column = Column(name = "cro_uf")),
                AttributeOverride(name = "number", column = Column(name = "cro_number"))
        )
        val cro: CRO,
        val name: String,
        val cpf: String,
        val rg: String,
        val genre: Genre,
        @Temporal(TemporalType.DATE)
        val birthDate: Date,
        @OneToMany(cascade = [CascadeType.ALL])
        val contacts: List<Contact>,
        @OneToMany(cascade = [CascadeType.ALL])
        val address: List<Address>,
        @Temporal(TemporalType.TIMESTAMP) @CreatedDate
        val createdAt: Date = Date.from(Instant.now()),
        @LastModifiedDate
        val updatedAt: Date? = null
)

package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.converter.LocalDateTimeConverter
import br.com.marceloazevedo.dentist.odonto.api.enum.ContactType
import br.com.marceloazevedo.dentist.odonto.api.integration.exchange.request.ContactRequest
import com.amazonaws.services.dynamodbv2.datamodeling.*
import java.time.LocalDateTime
import java.util.*

@DynamoDBDocument
data class Contact(
        @DynamoDBAutoGeneratedKey
        val id: String = UUID.randomUUID().toString(),
        @DynamoDBAttribute
        val name: String,
        @DynamoDBAttribute
        val value: String,
        @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
        val type: ContactType,
        @DynamoDBTypeConverted(converter = LocalDateTimeConverter::class)
        val createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(contactRequest: ContactRequest?) : this(
            UUID.randomUUID().toString(), contactRequest?.name!!, contactRequest.value!!, contactRequest.type!!, LocalDateTime.now()
    )
}

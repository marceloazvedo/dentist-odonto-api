package br.com.marceloazevedo.dentist.odonto.api.model

import br.com.marceloazevedo.dentist.odonto.api.enum.UF
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped

@DynamoDBDocument
data class CRO(
        @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
        val uf: UF,
        @DynamoDBAttribute
        val number: String
)

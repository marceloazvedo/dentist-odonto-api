package br.com.marceloazevedo.dentist.odonto.api.converter

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import java.time.LocalDate
import java.time.LocalDateTime

class LocalDateConverter : DynamoDBTypeConverter<String, LocalDate> {

    override fun convert(date: LocalDate?): String = date.toString()

    override fun unconvert(date: String?): LocalDate = LocalDate.parse(date)
}

class LocalDateTimeConverter : DynamoDBTypeConverter<String, LocalDateTime> {

    override fun convert(date: LocalDateTime?): String = date.toString()

    override fun unconvert(date: String?): LocalDateTime = LocalDateTime.parse(date)
}

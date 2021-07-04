package br.com.marceloazevedo.dentist.odonto.api.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.parse(this, formatter)
}

fun LocalDateTime.parserToString(): String =
    this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

fun LocalDate.parserToString(): String =
    this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

fun isCpfValid(document: String): Boolean {
    if (document.isEmpty()) return false

    val numbers = document.filter { it.isDigit() }.map {
        it.toString().toInt()
    }

    if (numbers.size != 11) return false

    //repeticao
    if (numbers.all { it == numbers[0] }) return false

    //digito 1
    val dv1 = ((0..8).sumOf { (it + 1) * numbers[it] }).rem(11).let {
        if (it >= 10) 0 else it
    }

    val dv2 = ((0..8).sumOf { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
        if (it >= 10) 0 else it
    }

    return numbers[9] == dv1 && numbers[10] == dv2
}

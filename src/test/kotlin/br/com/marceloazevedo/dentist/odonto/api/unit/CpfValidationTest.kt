package br.com.marceloazevedo.dentist.odonto.api.unit

import br.com.marceloazevedo.dentist.odonto.api.util.isCpfValid
import org.junit.jupiter.api.Test

class CpfValidationTest {

    companion object {
        val NOT_VALID_CPFS = listOf(
                "17734532493", "00135829304", "12070275460", "00138625504", "00127436714", "00136123694", "13090940977",
                "01303816444", "00704535034", "13584249479", "00107918984", "00105226924", "00132193014", "13346876401",
                "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777",
                "88888888888", "99999999999", "00000000000"
        )
        val VALID_CPFS = listOf(
                "28426946003", "00720462010", "09928001022", "50717550095", "30125309082", "21904093060", "61410561054",
                "70661624048", "94561297057", "18195464033", "59522945021", "48717315026", "45529121004", "32250526044"
        )
    }

    @Test
    fun `should not be a valid cpf`() {
        NOT_VALID_CPFS.forEach { assert(!isCpfValid(it)) }
    }

    @Test
    fun `should be a valid cpf`() {
        VALID_CPFS.forEach { assert(isCpfValid(it)) }
    }

}
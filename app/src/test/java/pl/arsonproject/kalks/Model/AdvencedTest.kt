package pl.arsonproject.kalks.Model

import org.junit.Test

import org.junit.Assert.*
import pl.arsonproject.calculator.Model.Advenced
import pl.arsonproject.calculator.Model.Simple

class AdvencedTest {

    val advenced = Advenced()

    @Test
    fun exponenetation() {
        assertEquals(
            4.toBigDecimal(),
            advenced.exponenetation(Advenced.Exp(Simple.Num(99999999999999.toBigDecimal()), Simple.Num(99.toBigDecimal())))
        )
    }

    @Test
    fun exponenetationTwo() {
        assertEquals(
            4.toBigDecimal(),
            advenced.exponenetation(Advenced.Exp(Simple.Num(2.toBigDecimal()), Simple.Num(2.toBigDecimal())))
        )
    }

    @Test
    fun rooting() {
        assertEquals(
            2.0.toBigDecimal(),
            advenced.rooting(Advenced.Root(Simple.Num(4.toBigDecimal())))
        )
    }

    @Test
    fun log() {
        assertEquals(
            1.0.toBigDecimal(),
            advenced.log(Advenced.Log(Simple.Num(10.toBigDecimal()), Simple.Num(10.toBigDecimal())))
        )
    }

    @Test
    fun logTwo() {
        assertEquals(
            1.0.toBigDecimal(),
            advenced.log(Advenced.Log(Simple.Num(10.toBigDecimal()), Simple.Num(2.toBigDecimal())))
        )
    }

    @Test
    fun sin() {
    }

    @Test
    fun cos() {
    }

    @Test
    fun tan() {
    }
}
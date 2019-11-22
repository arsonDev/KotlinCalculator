package pl.arsonproject.kalks.Model

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.arsonproject.calculator.Model.Simple
import pl.arsonproject.calculator.Model.Simple.*
import java.math.BigDecimal

class SimpleTest {

    private val simple = Simple()

    //2 + 2
    @Test
    fun addisionTest() {
        assertEquals(
            4.toBigDecimal(),
            simple.adding(Sum(Num(2.toBigDecimal()), Num(2.toBigDecimal())))
        )
    }

    //(2+2)*2
    @Test
    fun multiplyAddtest() {
        assertEquals(
            BigDecimal.valueOf(8),
            simple.multiplication(
                Multiply(
                    Num(
                        simple.adding(
                            Sum(
                                Num(BigDecimal.valueOf(2)),
                                Num(BigDecimal.valueOf(2))
                            )
                        )
                    ), Num(BigDecimal.valueOf(2))
                )
            )
        )
    }

    // 10 / 5
    @Test
    fun dividingTest() {
        assertEquals(
            BigDecimal.valueOf(5),
            simple.dividing(
                Divide(
                    Num(BigDecimal.valueOf(10)),
                    Num(BigDecimal.valueOf(2))
                )
            )
        )
    }

    //10/5
    @Test
    fun dividingWithDecimalsTest() {
        assertEquals(
            BigDecimal.valueOf(0.2.toLong()),
            simple.dividing(
                Divide(
                    Num(BigDecimal.valueOf(1)),
                    Num(BigDecimal.valueOf(5))
                )
            )
        )
    }

    @Test(expected = ArithmeticException::class)
    fun dividingByZeroTest() {
        simple.dividing(
            Divide(
                Num(BigDecimal.valueOf(10)),
                Num(BigDecimal.valueOf(0))
            )
        )
        simple.dividing(
            Divide(
                Num(0.toBigDecimal()),
                Num(10.toBigDecimal())
            )
        )
    }

    @Test
    fun substraction() {
        assertEquals(
            20.toBigDecimal(), simple.substracion(
                Subtract(
                    Num(30.toBigDecimal()),
                    Num(10.toBigDecimal())
                )
            )
        )
        assertEquals(
            (-10).toBigDecimal(),
            simple.substracion(
                Subtract(
                    Num(
                        simple.adding(
                            Sum(
                                Num(10.toBigDecimal()),
                                Num(10.toBigDecimal())
                            )
                        )
                    ),
                    Num(
                        simple.multiplication(
                            Multiply(
                                Num(15.toBigDecimal()),
                                Num(2.toBigDecimal())
                            )
                        )
                    )
                )
            )
        )
    }
}
package pl.arsonproject.calculator.Model

import java.math.BigDecimal

open class Simple {

    fun adding(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Sum)
                return adding(e.left) + adding(e.right)
        } catch (e: Exception) {
            throw java.lang.IllegalArgumentException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun substracion(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Subtract)
                return substracion(e.left) - substracion(e.right)
        } catch (e: Exception) {
            throw IllegalArgumentException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun multiplication(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Multiply)
                return multiplication(e.left) * multiplication(e.right)
        } catch (e: Exception) {
            throw IllegalArgumentException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun dividing(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Divide)
                return dividing(e.left).divide(dividing(e.right))
        } catch (e: Exception) {
            throw java.lang.ArithmeticException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    interface Expr
    class Num(val value: BigDecimal) : Expr
    class Sum(val left : Expr,val  right : Expr) : Expr
    class Divide(val left : Expr,val  right : Expr) : Expr
    class Multiply(val left : Expr,val  right : Expr) : Expr
    class Subtract(val left: Expr, val right: Expr) : Expr
}
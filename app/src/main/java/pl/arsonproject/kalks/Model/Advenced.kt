package pl.arsonproject.calculator.Model

import java.math.BigDecimal
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sqrt

class Advenced : Simple() {

    fun exponenetation(e: Expr): BigDecimal {
        try {
            if (e is Exp)
                return (e.left as Num).value.toDouble().pow((e.right as Num).value.toInt()).toBigDecimal()
        } catch (e: Exception) {
            throw java.lang.ArithmeticException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun rooting(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Root)
                return sqrt(rooting(e.left).toDouble()).toBigDecimal()
        } catch (e: Exception) {
            throw IllegalArgumentException("Wprowadź poprawne liczby")
        }
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun log(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Log)
                return log(log(e.left).toDouble(), log(e.right).toDouble()).toBigDecimal()
        } catch (e: java.lang.Exception) {
            throw ArithmeticException("Wyrażenie błędne")
        }
        throw ArithmeticException("Wyrażenie błędne")
    }

    fun sin(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Sin)
                return kotlin.math.sin(sin(e.left).toDouble()).toBigDecimal()
        } catch (e: java.lang.Exception) {
            throw java.lang.ArithmeticException("Wyrażenie błędne")
        }
        throw java.lang.ArithmeticException("Wyrażenie błędne")
    }


    fun cos(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Cos)
                return kotlin.math.cos(cos(e.left).toDouble()).toBigDecimal()
        } catch (e: java.lang.Exception) {
            throw java.lang.ArithmeticException("Wyrażenie błędne")
        }
        throw java.lang.ArithmeticException("Wyrażenie błędne")
    }


    fun tan(e: Expr): BigDecimal {
        try {
            if (e is Num)
                return e.value
            if (e is Tan)
                return kotlin.math.tan(tan(e.left).toDouble()).toBigDecimal()
        } catch (e: java.lang.Exception) {
            throw java.lang.ArithmeticException("Wyrażenie błędne")
        }
        throw java.lang.ArithmeticException("Wyrażenie błędne")
    }

//    fun perc(e : Expr) : BigDecimal{
//        try {
//            if (e is Num)
//                return e.value
//            if (e is Perc)
//                return (Perc(e.left))
//        }catch (e  :java.lang.Exception){
//            throw java.lang.ArithmeticException("Wyrażenie błędne")
//        }
//        throw java.lang.ArithmeticException("Wyrażenie błędne")
//    }

    class Exp(val left: Expr, val right: Expr) : Expr
    class Root(val left: Expr) : Expr
    class Log(val left: Expr, val right: Expr) : Expr
    class Sin(val left: Expr) : Expr
    class Cos(val left: Expr) : Expr
    class Tan(val left: Expr) : Expr
    class Perc(val left: Expr) : Expr
}
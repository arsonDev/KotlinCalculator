package pl.arsonproject.calculator.Model

import java.math.BigInteger

class Simple {

    fun adding(e : Expr) : BigInteger{
        if (e is Num)
            return e.value
        if (e is Sum)
            return adding(e.left) + adding(e.right)
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun substracion(e : Expr) : BigInteger{
        if (e is Num)
            return e.value
        if (e is Minus)
            return substracion(e.left) - substracion(e.right)
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun multiplication(e : Expr) : BigInteger{
        if (e is Num)
            return e.value
        if (e is Multiply)
            return multiplication(e.left) * multiplication(e.right)
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    fun division(e : Expr) : BigInteger{
        if (e is Num)
            return e.value
        if (e is Divide)
            return division(e.left) + division(e.right)
        throw IllegalAccessException("Nieznane wyrażenie")
    }

    interface Expr
    class Num(val value : BigInteger) : Expr
    class Sum(val left : Expr,val  right : Expr) : Expr
    class Divide(val left : Expr,val  right : Expr) : Expr
    class Multiply(val left : Expr,val  right : Expr) : Expr
    class Minus(val left : Expr,val  right : Expr) : Expr
}
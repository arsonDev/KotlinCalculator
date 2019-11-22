package pl.arsonproject.kalks.ui.simple

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.arsonproject.calculator.Model.Actions
import pl.arsonproject.calculator.Model.Actions.*
import pl.arsonproject.calculator.Model.Simple
import java.math.BigDecimal
import java.text.DecimalFormat

open class SimpleViewModel : ViewModel(), Observable {

    @Bindable
    val resultCalc = ObservableField<String>("")

    @Bindable
    val fullCalculation = ObservableField<String>("")

    val errorMessage = MutableLiveData<String>("")

    val resetAnimObject = MutableLiveData<Boolean>(true)
    private val simpleCalc = Simple()
    private lateinit var _calcResult: BigDecimal
    private var _left: BigDecimal? = null
    private var _right: BigDecimal? = null
    private var _secondNum = false
    private var _currentAction: Actions? = null

    fun clearAll() {
        resultCalc.set("")
        fullCalculation.set("")
        _left = null
        _right = null
        _secondNum = false
        _calcResult = 0.toBigDecimal()
        _currentAction = null
        if (!resetAnimObject.value!!)
            resetAnimObject.value = false
    }

    fun button1() {
        fullCalculation.set(fullCalculation.get() + "1")
        doOperation(_currentAction)
    }

    fun button2() {
        fullCalculation.set(fullCalculation.get() + "2")
        doOperation(_currentAction)
    }

    fun button3() {
        fullCalculation.set(fullCalculation.get() + "3")
        doOperation(_currentAction)
    }

    fun button4() {
        fullCalculation.set(fullCalculation.get() + "4")
        doOperation(_currentAction)
    }

    fun button5() {
        fullCalculation.set(fullCalculation.get() + "5")
        doOperation(_currentAction)
    }

    fun button6() {
        fullCalculation.set(fullCalculation.get() + "6")
        doOperation(_currentAction)
    }

    fun button7() {
        fullCalculation.set(fullCalculation.get() + "7")
        doOperation(_currentAction)
    }

    fun button8() {
        fullCalculation.set(fullCalculation.get() + "8")
        doOperation(_currentAction)
    }

    fun button9() {
        fullCalculation.set(fullCalculation.get() + "9")
        doOperation(_currentAction)
    }

    fun button0() {
        fullCalculation.set(fullCalculation.get() + "0")
        doOperation(_currentAction)
    }

    fun buttonDot() {
        if (fullCalculation.get()?.length!! > 0)
            if (fullCalculation.get()?.takeLast(1) != ".")
                try {
                    var a = fullCalculation.get()?.split(" ")
                    if (!a?.last()?.contains(".")!!)
                        fullCalculation.set(fullCalculation.get() + ".")
                } catch (e: java.lang.Exception) {
                    errorMessage.value = "Liczba nierpawidłowa"
                }
    }

    fun multiplyButton() {
        _currentAction = MULTIPLICATION
        if (_left != null)
            _secondNum = true
        if (fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank())
            fullCalculation.set(fullCalculation.get()?.dropLast(3))
        fullCalculation.set(fullCalculation.get() + " x ")
    }

    fun divisionButton() {
        _currentAction = DIVISION
        if (_left != null)
            _secondNum = true
        if (fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank())
            fullCalculation.set(fullCalculation.get()?.dropLast(3))
        fullCalculation.set(fullCalculation.get() + " / ")
    }

    fun addingButton() {
        _currentAction = ADDITION
        if (_left != null)
            _secondNum = true
        if (fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank())
            fullCalculation.set(fullCalculation.get()?.dropLast(3))
        fullCalculation.set(fullCalculation.get() + " + ")
    }

    fun subtractingButton() {
        _currentAction = SUBTRACTION
        if (_left != null)
            _secondNum = true
        if (fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank())
            fullCalculation.set(fullCalculation.get()?.dropLast(3))
        fullCalculation.set(fullCalculation.get() + " - ")
    }

    open fun doOperation(actions: Actions?) {
        try {
            operation {
                when (actions) {
                    ADDITION -> simpleCalc.adding(
                        Simple.Sum(
                            Simple.Num(_left!!),
                            Simple.Num(_right!!)
                        )
                    )
                    SUBTRACTION -> simpleCalc.substracion(
                        Simple.Subtract(
                            Simple.Num(_left!!),
                            Simple.Num(_right!!)
                        )
                    )
                    DIVISION -> simpleCalc.dividing(
                        Simple.Divide(
                            Simple.Num(_left!!),
                            Simple.Num(_right!!)
                        )
                    )
                    MULTIPLICATION -> simpleCalc.multiplication(
                        Simple.Multiply(
                            Simple.Num(_left!!),
                            Simple.Num(_right!!)
                        )
                    )
                    else -> BigDecimal(Double.NaN)
                }
            }
        } catch (e: Exception) {
            errorMessage.value = e.message
        }
    }

    open fun operation(operation: () -> BigDecimal) {
        if (!fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank()) {
            if (!_secondNum) {
                _left = fullCalculation.get()?.replace(" ", "")?.toBigDecimal()
                    ?: throw IllegalArgumentException("Podaj pierwszą liczbę")
            } else {
                _right = fullCalculation.get()?.takeLastWhile { it.isDigit() }?.toBigDecimal()
                    ?: throw IllegalArgumentException("Podaj druga liczbę")
                _secondNum = true
            }
            if (_secondNum) {
                if (_left != null && _right != null) {
                    _calcResult = operation()

                    val decimalFormat = DecimalFormat("0.000000000E0")
                    if (_calcResult > BigDecimal(999999999999999999))
                        resultCalc.set(decimalFormat.format(_calcResult))
                    else
                        resultCalc.set(_calcResult.toString())
                    _left = _calcResult
                    _right = null
                }
            }
        }
    }


    fun deleteDigit() {
        if (fullCalculation.get()?.takeLastWhile { it.isDigit() }.isNullOrBlank())
            fullCalculation.set(fullCalculation.get()?.dropLast(3))
        else
            fullCalculation.set(fullCalculation.get()?.dropLast(1))
    }

    @delegate:Transient
    private val mCallBacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.add(callback)
    }
}
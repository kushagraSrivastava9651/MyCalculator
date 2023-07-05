package com.example.my_calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    private var lastNumeric:Boolean=false
    private var lastdot=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tv)



    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastdot=false

    }
fun removeZeroAfterDot(result: String):String{
    if(result.contains(".0")){
        return result.substring(0,result.length-2)
    }
    return result
}
    fun onDecimal(view:View){
if(lastNumeric && !lastdot){
    tvInput?.append(".")
    lastNumeric=false
    lastdot=true
        }
    }

    fun onclear(view: View){
        tvInput?.text=""
    }
fun onOperator(view: View){
    tvInput?.text?.let {
        if(lastNumeric && !operator(it.toString())){
            tvInput?.append((view as Button).text)
            lastdot=false
            lastNumeric=false
        }
    }
}
      fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prifix=""
            try {
                if(tvValue.startsWith("-")){
                    prifix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitvalue = tvValue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prifix.isNotEmpty()){
                        one=prifix+one
                    }
                    tvInput?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }
              else  if(tvValue.contains("+")) {
                    val splitvalue = tvValue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prifix.isNotEmpty()){
                        one=prifix+one
                    }
                    tvInput?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }
                else  if(tvValue.contains("/")) {
                    val splitvalue = tvValue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prifix.isNotEmpty()){
                        one=prifix+one
                    }
                    tvInput?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }
                else  if(tvValue.contains("*")) {
                    val splitvalue = tvValue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prifix.isNotEmpty()){
                        one=prifix+one
                    }
                    tvInput?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
   private  fun operator(value:String):Boolean {
       if (value.startsWith("-")) {
         return  false
       } else {
         return  value.contains("+") || value.contains("-") || value.contains("/") || value.contains("*")
       }
   }
}
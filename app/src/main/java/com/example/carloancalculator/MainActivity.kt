package com.example.carloancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.icu.util.ULocale.getCountry
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener {
            calculateLoan()
        }
    }

    private fun calculateLoan(){
        //TODO get all inputs and display outputs

        //Validation
        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error_input))
            return
        }

        if(editTextDownPayment.text.isEmpty()){
            editTextDownPayment.setError(getString(R.string.error_input))
            return
        }

        if(editTextLoanPeriod.text.isEmpty()){
            editTextLoanPeriod.setError(getString(R.string.error_input))
            return
        }

        if(editTextInterestRate.text.isEmpty()) {
            editTextInterestRate.setError(getString(R.string.error_input))
            return
        }

        val carPrice = editTextCarPrice.text.toString().toInt()
        val downPayment = editTextDownPayment.text.toString().toFloat()
        val loanPeriod = editTextLoanPeriod.text.toString().toInt()
        val interestRate = (editTextInterestRate.text.toString().toFloat())/100

        val carLoan = carPrice - downPayment
        val interest = carLoan * interestRate * loanPeriod
        val monthlyRepayment = (carLoan+interest)/loanPeriod/12

        val localeCountry=Locale.getDefault()
        val currencyCode= Currency.getInstance(localeCountry).getSymbol()

        textViewLoan.setText(getString(R.string.loan) +currencyCode+" ${String.format("%.2f",carLoan)}")
        textViewInterest.setText(getString(R.string.interest)+currencyCode+" ${String.format("%.2f",interest)}")
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment)+currencyCode+" ${String.format("%.2f",monthlyRepayment)}")

        Toast.makeText(this, "Calculate Loan", Toast.LENGTH_SHORT).show()
    }

    fun reset(view:View){
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
        textViewLoan.setText("")
        textViewInterest.setText("")
        textViewMonthlyRepayment.setText("")
    }
}

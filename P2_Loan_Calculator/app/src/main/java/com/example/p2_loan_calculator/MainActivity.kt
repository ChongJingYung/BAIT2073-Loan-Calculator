package com.example.p2_loan_calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.p2_loan_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            //TODO: Get inputs from use and calculate the monthly payment

            when {
                binding.editTextPrice.text.toString().isEmpty() -> {
                    binding.editTextPrice.error = "Required field"
                }
                binding.editTextDownPayment.text.toString().isEmpty() -> {
                    binding.editTextDownPayment.error = "Required field"
                }
                binding.editTextInterestRate.text.toString().isEmpty() -> {
                    binding.editTextInterestRate.error = "Required field"
                }
                else -> {
                    val price : Int = Integer.parseInt(binding.editTextPrice.text.toString())
                    val downPayment : Int = Integer.parseInt(binding.editTextDownPayment.text.toString())
                    val interestRate : Double = binding.editTextInterestRate.text.toString().toDouble()

                    val index: Int = binding.spinnerYear.selectedItemPosition //Get selected Spinner value
                    var year: Int = 0
                    when (index) {
                        0 -> {
                            year = 3
                        }
                        1 -> {
                            year = 4
                        }
                        2 -> {
                            year = 5
                        }
                        3 -> {
                            year = 6
                        }
                        4 -> {
                            year = 7
                        }
                    }

                    val carLoan = price - downPayment
                    val interest = carLoan * interestRate * year
                    val monthlyRepayment = (carLoan + interest) / year / 12

                    //Process set value
                    val textViewPayment = binding.textViewPayment
                    textViewPayment.text = monthlyRepayment.toString()
                }
            }

        }

        binding.buttonReset.setOnClickListener {
            //TODO: Reset all Views and outputs
            val resetPayment = binding.editTextPrice
            resetPayment.setText("")

            val resetDownPayment = binding.editTextDownPayment
            resetDownPayment.setText("")

            val resetInterestRate = binding.editTextInterestRate
            resetInterestRate.setText("")

            val resetIndex  = binding.spinnerYear
            resetIndex.setSelection(0)

            val resetMonthlyPayment = binding.textViewPayment
            resetMonthlyPayment.text = ""
        }
    }

}
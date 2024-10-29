// MainActivity.kt
package com.example.th_android
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sourceCurrencySpinner = findViewById<Spinner>(R.id.spinnerSourceCurrency)
        val targetCurrencySpinner = findViewById<Spinner>(R.id.spinnerTargetCurrency)
        val sourceAmountEditText = findViewById<EditText>(R.id.editTextSourceAmount)
        val targetAmountEditText = findViewById<EditText>(R.id.editTextTargetAmount)

        val currencies = arrayOf("USD", "VND", "EUR", "JPY")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)
        sourceCurrencySpinner.adapter = adapter
        targetCurrencySpinner.adapter = adapter

        sourceAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency(sourceAmountEditText, targetAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun convertCurrency(source: EditText, target: EditText, sourceSpinner: Spinner, targetSpinner: Spinner) {
        val amount = source.text.toString().toDoubleOrNull() ?: return
        val sourceCurrency = sourceSpinner.selectedItem.toString()
        val targetCurrency = targetSpinner.selectedItem.toString()

        val rate = getConversionRate(sourceCurrency, targetCurrency)
        val convertedAmount = amount * rate
        target.setText(convertedAmount.toString())
    }

    private fun getConversionRate(source: String, target: String): Double {
        return when (source to target) {
            "USD" to "VND" -> 23000.0
            "VND" to "USD" -> 1 / 23000.0
            "EUR" to "USD" -> 1.1
            // Thêm các tỷ giá khác tại đây
            else -> 1.0
        }
    }
}

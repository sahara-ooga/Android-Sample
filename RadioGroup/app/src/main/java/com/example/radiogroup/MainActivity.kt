package com.example.radiogroup

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var radioGroup: RadioGroup
    lateinit var radioButton: RadioButton
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.findView()
    }

    private fun findView() {
        radioGroup = findViewById(R.id.radioGroup)
        textView = findViewById(R.id.text_view_selected)
        val buttonApply = findViewById<Button>(R.id.button_apply)
        buttonApply.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val checkedRadioId = radioGroup.checkedRadioButtonId
                radioButton = findViewById(checkedRadioId)
                textView.text = "Your choice: " + radioButton.text
            }
        })
    }

    fun checkButton(v: View) {
        val checkedRadioId = radioGroup.checkedRadioButtonId
        radioButton = findViewById(checkedRadioId)

        Toast.makeText(this, "Selected Radio Button: " + radioButton.text, Toast.LENGTH_SHORT)
            .show()
    }
}

package com.example.bmiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 計算ボタン押下時処理
        calcuration_button.setOnClickListener {

            // editTextから文字列を取得
            val height = input_height.text.toString()
            val weight = input_weight.text.toString()

            // BMIを計算するためにfloat型に変換
            val floHeight = (height.toFloat() * height.toFloat()) / 10000
            val floWeight = weight.toFloat()

            // BMIの計算
            val result = (Math.round((floWeight / floHeight) * 10)).toFloat()

            // 計算したBMIをTextViewに挿入
            val bmi : String = (result / 10).toString()
            val bmiText : TextView = findViewById(R.id.BMI_text)
            bmiText.text = bmi
        }
    }
}

package com.example.bmiapplication

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calcuration.*
import java.text.SimpleDateFormat
import java.util.*

class CalculationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calcuration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 計算ボタン押下時処理
        calcuration_button.setOnClickListener {

            // editTextから文字列を取得
            val height = input_height.text.toString()
            val weight = input_weight.text.toString()

            if (height == "" || weight == "") {
                CalculationLogic.showErrorDialog("数値が入力されていません", this.activity!!)
                return@setOnClickListener
            } else if (height.length > 5 || weight.length > 5) {
                CalculationLogic.showErrorDialog("入力値が大きすぎます", this.activity!!)
                return@setOnClickListener
            }

            val bmi = CalculationLogic.calculationBmi(height, weight)
            BMI_text.text = bmi
        }

        // 保存ボタン押下時処理
        save_button.setOnClickListener {

            // editTextから文字列を取得
            val height = input_height.text.toString()
            val weight = input_weight.text.toString()

            if (height == "" || weight == "") {
                CalculationLogic.showErrorDialog("数値が入力されていません", this.activity!!)
                return@setOnClickListener
            } else if (height.length > 5 || weight.length > 5) {
                CalculationLogic.showErrorDialog("入力値が大きすぎます", this.activity!!)
                return@setOnClickListener
            }

            val bmi = BMI_text.text.toString()

            val data: SharedPreferences = this.activity!!.getSharedPreferences("Data", Context.MODE_PRIVATE)
            val editor = data.edit()

            val date = SimpleDateFormat("yyyy/M/d", Locale.JAPAN).format(Date())

            val bmiData = CalculationLogic.dataSaveChangeJson(date, height.toFloat(), weight.toFloat(), bmi.toFloat(),input_memo.text.toString(),
                this.activity!!
            )

            editor.putString("BMI", bmiData.toString())

            editor.apply()
        }

        delete_button.setOnClickListener {
            val data: SharedPreferences = this.activity!!.getSharedPreferences("Data", Context.MODE_PRIVATE)
            val date = SimpleDateFormat("yyyy/M/d", Locale.JAPAN).format(Date())
            data.getString("BMI","")
        }
    }

}

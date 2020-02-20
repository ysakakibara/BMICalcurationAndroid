package com.example.bmiapplication

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject

class CalculationLogic {

    companion object {

        fun showErrorDialog(errorMessage: String, activity: Activity) {
            AlertDialog.Builder(activity)
                .setTitle("エラー")
                .setMessage(errorMessage)
                .setPositiveButton("OK") { dialog, which -> }
                .show()
        }

        fun calculationBmi(height: String, weight: String): String {

            // BMIを計算するためにfloat型に変換
            val floHeight = (height.toFloat() * height.toFloat()) / 10000
            val floWeight = weight.toFloat()

            // BMIの計算
            val result = (Math.round((floWeight / floHeight) * 10)).toFloat()

            // BMIをString型に変換　
            val bmi: String = (result / 10).toString()

            return bmi
        }

        fun dataSaveChangeJson(
            date: String,
            height: Float,
            weight: Float,
            bmi: Float,
            memo: String,
            activity: Activity
        ): JSONObject {

            val sharedPreferences =
                activity.getSharedPreferences("Data", Context.MODE_PRIVATE)

            val savedAllJsonString = sharedPreferences.getString("BMI", "{}")

            val savedAllJson = JSONObject(savedAllJsonString)
            val saveJson = JSONObject()
            saveJson.put("height", height)
            saveJson.put("weight", weight)
            saveJson.put("BMI", bmi)
            saveJson.put("memo", memo)

            savedAllJson.put(date, saveJson)
            return savedAllJson
        }
    }
}
package com.example.bmiapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_calcuration.*

class CalculationFragment : Fragment() {
//    private var listener: OnFragmentInteractionListener? = null

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
                showErrorDialog("文字入れて")
                return@setOnClickListener
            } else if (height.length > 5 || weight.length > 5) {
                showErrorDialog("文字多いよ")
                return@setOnClickListener
            }

            // BMIを計算するためにfloat型に変換
            val floHeight = (height.toFloat() * height.toFloat()) / 10000
            val floWeight = weight.toFloat()

            // BMIの計算
            val result = (Math.round((floWeight / floHeight) * 10)).toFloat()

            // 計算したBMIをTextViewに挿入
            val bmi : String = (result / 10).toString()
            BMI_text.text = bmi
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        AlertDialog.Builder(this.activity!!)
            .setTitle("エラー")
            .setMessage(errorMessage)
            .setPositiveButton("わかった"){ dialog, which -> }
            .show()
    }
}

package com.example.bmiapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_calcuration.*

class CalcurationFragment : Fragment() {
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
                AlertDialog.Builder(this.activity!!)
                    .setTitle("エラー")
                    .setMessage("文字入れて")
                    .setPositiveButton("わかった"){ dialog, which -> }
                    .show()
                return@setOnClickListener
            } else if (height.length > 5 || weight.length > 5) {
                AlertDialog.Builder(this.activity!!)
                    .setTitle("エラー")
                    .setMessage("文字多いよ")
                    .setPositiveButton("わかった"){ dialog, which -> }
                    .show()
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

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     *
//     *
//     * See the Android Training lesson [Communicating with Other Fragments]
//     * (http://developer.android.com/training/basics/fragments/communicating.html)
//     * for more information.
//     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }
}

package com.dovizkuruogren

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.*
import com.dovizkuruogren.databinding.ActivityMainBinding
import com.dovizkuruogren.service.XmlService

class MainActivity : AppCompatActivity() {
    lateinit var spinner:Spinner
    lateinit var txtTimeInfo:TextView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val xmlService = XmlService()

        val policy= ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        txtTimeInfo=binding.txtTimeInfo
        txtTimeInfo.text =xmlService.dayMonthYear()+" "+ xmlService.xmlDateTxt()
        val arr = xmlService.xmlCurrency()
        val list = arrayListOf<String>()
        list.addAll(arr.map { it.isim })
        list.add(0,"Kuru Seçiniz")

        spinner = binding.spinner

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, list
            )
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if (position != 0) {
                    binding.linearLayoutSettingsAndBuyings.visibility = View.VISIBLE
                    val currency = arr.get(position-1)
                    binding.txtBanknoteBuying.text = "Efektif Alış \n ${currency.banknoteBuying}"
                    binding.txtBanknoteSelling.text = "Efektif Satış \n ${currency.banknoteSelling}"
                    binding.txtForexBuying.text = "Borsa Alış \n ${currency.forexBuying}"
                    binding.txtForexSelling.text = "Borsa Satış \n ${currency.forexSelling}"
                }
                else{
                    binding.linearLayoutSettingsAndBuyings.visibility= View.GONE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }


}
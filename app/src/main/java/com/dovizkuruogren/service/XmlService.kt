package com.dovizkuruogren.service

import android.util.Log
import com.dovizkuruogren.model.Currency
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlService {
    val rootUrl="https://www.tcmb.gov.tr/kurlar/"
    val xmlUrl = "today.xml"
    fun xmlCurrency() : List<Currency> {
        val arr = mutableListOf<Currency>()
        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        val elements: Elements = doc.getElementsByTag("Currency")
        for( item in elements ) {
            val Isim = item.getElementsByTag("Isim").text()
            val ForexBuying = item.getElementsByTag("ForexBuying").text()
            val ForexSelling = item.getElementsByTag("ForexSelling").text()
            val BanknoteBuying = item.getElementsByTag("BanknoteBuying").text()
            val BanknoteSelling = item.getElementsByTag("BanknoteSelling").text()

            val currency = Currency(Isim, ForexBuying, ForexSelling, BanknoteBuying,BanknoteSelling)
            arr.add(currency)
        }
        return arr
    }
    fun xmlDateTxt():String {

        val xml: Document = Jsoup.connect(rootUrl+xmlUrl).get()
        var htmlurl=""
        for(item in xml.childNodes()){
            if(item.attr("href").isNotEmpty()){
                htmlurl=item.attr("href")
            }
        }
        Log.d("Test2",htmlurl)

        val html: Document = Jsoup.connect(rootUrl+htmlurl).get()
        val text=html.select("h1").text()

        return text
    }


    fun dayMonthYear():String{
        val tarih: Document = Jsoup.connect(rootUrl+xmlUrl).get()
        var Tarih:String=""
        for(item in tarih.childNodes()){
            if (item.attr("Tarih").isNotEmpty()) {
                Tarih = item.attr("Tarih")
            }
        }
        Log.d("atakantest",Tarih)
        return Tarih
    }
    }




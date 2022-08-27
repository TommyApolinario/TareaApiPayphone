package com.ejemplo1.apipayphone

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
    var colaRequest: RequestQueue? = null
    private val TOKEN =
        "kLNZZBXH_EUd7i7iMqpb62fNcP1-0frAyGI9iQmN_uBaTaOfnuN_c3bQS23bN5YhLy0JKrd36GSLCpV-lMyVunSGx78Xrb6i5YvC7M-ZxRz452t_elOG1ay1A5KObGn--HxQYYf8s8-2E8KKpm2Njghdc0xWp_cHm_-EXikJkU2GGaCXobDe7L14QkaGmG_jrppc_xqp4eDth8a8gi7hsp8XDi2VXlGClHdLtDHYyKCndojvF_qptwipn_VcYaZbfFmJKqFd6Cei9IpST9LSJGyDQ660m_FNtp8Vt7-0SecNzywUfS2xE4n2sFIozzvb0a3rGQ"
    var pagosItems: MutableList<HistorialPagoItem>? = null
    var elementos: MutableList<String> = ArrayList()
    var itemListCode: AutoCompleteTextView? = null
    var adapterItem: ArrayAdapter<String>? = null
    var codigoSelecionado = ""
    var inputNumeroCelular: TextInputEditText? = null
    var inputMonto: TextInputEditText? = null
    var inputReferencia: TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Historial de pagos
        pagosItems = ArrayList()
        (pagosItems as ArrayList<HistorialPagoItem>).add(HistorialPagoItem("Pago #1", "Se realizo a 00023923", "32234234"))
        (pagosItems as ArrayList<HistorialPagoItem>).add(HistorialPagoItem("Pago #1", "Se realizo a 00023923", "32234234"))
        (pagosItems as ArrayList<HistorialPagoItem>).add(HistorialPagoItem("Pago #1", "Se realizo a 00023923", "32234234"))
        val historialAdapter = HistorialAdapter(pagosItems as ArrayList<HistorialPagoItem>, this)
        val recyclerView = findViewById<RecyclerView>(R.id.historial_pagos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = historialAdapter
        colaRequest = Volley.newRequestQueue(this)
        colaRequest!!.start()

        // asociar elementos
        inputNumeroCelular = findViewById<View>(R.id.inputNumeroCelular) as TextInputEditText
        inputMonto = findViewById<View>(R.id.inputMonto) as TextInputEditText
        inputReferencia = findViewById<View>(R.id.inputReferencia) as TextInputEditText
        itemListCode = findViewById(R.id.item_list_code)
        elementos.add("232")
        elementos.add("222")
        elementos.add("772")
        adapterItem = ArrayAdapter(this, R.layout.list_item, elementos)
        itemListCode.setAdapter(adapterItem)
        regiones
        itemListCode.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            codigoSelecionado = item
            Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_LONG).show()
        })
    }

    val regiones: Unit
        get() {
            val RequestRegiones: JsonArrayRequest = object : JsonArrayRequest(
                Method.GET,
                "https://pay.payphonetodoesposible.com/api/Regions",
                null,
                Response.Listener { response ->
                    elementos.clear()
                    try {
                        for (i in 0 until response.length()) {
                            elementos.add(
                                response.getJSONObject(i).getInt("prefixNumber").toString()
                            )
                            println(response.getJSONObject(i).getInt("prefixNumber"))
                        }
                    } catch (ex: JSONException) {
                        println(ex.toString())
                    }
                },
                Response.ErrorListener { }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): kotlin.collections.Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Authorization"] = "Bearer $TOKEN"
                    return params
                }
            }
            colaRequest!!.add(RequestRegiones)
        }


}

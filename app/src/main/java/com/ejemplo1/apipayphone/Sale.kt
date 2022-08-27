package com.ejemplo1.apipayphone
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

class Sale {
    var phoneNumber: String? = null
    var countryCode: String? = null
    var reference: String? = null
    var amount = 0
    var amountWithoutTax = 0
    var clientTransactionId: String? = null
    var currency: String? = null

    // convierte un Sale a Object Json
    @get:Throws(JSONException::class)
    val json: JSONObject
        get() {
            val gson = Gson()
            return JSONObject(gson.toJson(this))
        }
}

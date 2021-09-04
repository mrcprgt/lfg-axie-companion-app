package com.mrcprgt.lfgaxiecompanionapp.app.data.scholardata

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mrcprgt.lfgaxiecompanionapp.app.data.lfgslprecord.RemoteLfgRecord
import com.mrcprgt.lfgaxiecompanionapp.app.data.slprecord.RemoteSlpResponse
import java.lang.reflect.Type

class ScholarDataDeserializer(
    private val withParentObject: Boolean = true
) : JsonDeserializer<RemoteScholarData> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemoteScholarData {
        val jsonObject = json.asJsonObject

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.fromJson(jsonObject, RemoteScholarData::class.java)
    }
}

class RemoteProfileDeserializer(
    private val withParentObject: Boolean = true
) : JsonDeserializer<RemoteProfile> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemoteProfile {
        val jsonObject = json.asJsonObject

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.fromJson(jsonObject, RemoteProfile::class.java)
    }
}

class RemotePvpProfileDeserializer(
    private val withParentObject: Boolean = true
) : JsonDeserializer<RemotePvPProfile> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemotePvPProfile {
        val jsonObject = json.asJsonObject

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.fromJson(jsonObject, RemotePvPProfile::class.java)
    }
}


class RemoteLFGResponseDeserializer(
    private val withParentObject: Boolean = true
) : JsonDeserializer<RemoteSlpResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemoteSlpResponse {
        val jsonObject = json.asJsonObject

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.fromJson(jsonObject, RemoteSlpResponse::class.java)
    }
}

class RemoteLFGRecordDeserializer(
    private val withParentObject: Boolean = true
) : JsonDeserializer<RemoteLfgRecord> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemoteLfgRecord {
        val jsonObject = json.asJsonObject

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.fromJson(jsonObject, RemoteLfgRecord::class.java)
    }
}

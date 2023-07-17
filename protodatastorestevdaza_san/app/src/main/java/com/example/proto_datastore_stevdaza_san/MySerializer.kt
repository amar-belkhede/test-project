package com.example.proto_datastore_stevdaza_san

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class MySerializer() : Serializer<Person> {
    override val defaultValue: Person = Person.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): Person {
        try {
            return Person.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.",exception)
        }
    }

    override suspend fun writeTo(t: Person, output: OutputStream) {
        t.writeTo(output)
    }
}
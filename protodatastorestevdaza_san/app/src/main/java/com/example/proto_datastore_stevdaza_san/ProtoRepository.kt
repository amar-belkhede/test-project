package com.example.proto_datastore_stevdaza_san

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import androidx.datastore.dataStore
class ProtoRepository(context:Context) {
    val dataStore = context.dataStore;


    val readProto: Flow<Person> = dataStore.data
        .catch { exception ->
            if(exception is IOException) {
                Log.d("error", exception.message.toString())
                emit(Person.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateValue(firstName: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setFirstName(firstName).build()
        }
    }
}
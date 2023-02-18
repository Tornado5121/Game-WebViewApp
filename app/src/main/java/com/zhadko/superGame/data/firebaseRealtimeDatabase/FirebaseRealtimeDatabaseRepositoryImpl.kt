package com.zhadko.superGame.data.firebaseRealtimeDatabase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FirebaseRealtimeDatabaseRepositoryImpl :
    FirebaseRealtimeDatabaseRepository {

    private val database =
        Firebase.database("https://supergame-c3062-default-rtdb.europe-west1.firebasedatabase.app")
    private val myRef = database.getReference("webViewLink")

    private val _webViewLink = MutableSharedFlow<String>()
    override val webViewLink = _webViewLink.asSharedFlow()

    private val myScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        setEventListeners()
    }

    private fun setEventListeners() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myScope.launch {
                    _webViewLink.emit(dataSnapshot.value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}
package com.example.movopfy.firebase.synchronization

import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.firebase.user.UserManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val FAVOURITES_DOCUMENT = "favourites"

class SynchronizationFavourites(private val userManager: UserManager) {

    private val db = Firebase.firestore

    fun synchronize() {
        if (userManager.getUID().isNotEmpty()) {
            db
                .collection(userManager.getUID())
                .document(FAVOURITES_DOCUMENT)
                .collection(API_CATEGORY_ANILIBRIA)
        }
    }

    fun addFavourites() {

    }

    fun updateFavourites() {
        if (userManager.getUID().isNotEmpty()) {
            db
                .collection(userManager.getUID())
                .document(FAVOURITES_DOCUMENT)
                .collection(API_CATEGORY_ANILIBRIA)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                       println(document.id)
                    }
                }
                .addOnFailureListener {
                    println(it)
                }
        }
    }
}
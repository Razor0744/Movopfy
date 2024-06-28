package com.example.movopfy.firebase.synchronization

import com.example.common.constants.API_CATEGORY_ANILIBRIA
import com.example.common.constants.API_CATEGORY_KINOPOISK
import com.example.common.extensions.dateWithTime
import com.example.movopfy.firebase.model.FirestoreFavouriteModel
import com.example.movopfy.firebase.model.FirestoreDataNewerResult
import com.example.movopfy.firebase.user.UserManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Calendar
import kotlin.coroutines.resume

const val FAVOURITES_DOCUMENT = "favourites"
const val DATE_FIELD = "date"
const val DATE_DOCUMENT = "date"

class FirestoreFavourites(private val userManager: UserManager) {

    private val db = Firebase.firestore

    suspend fun firestoreDataNewer(date: Int): FirestoreDataNewerResult =
        suspendCancellableCoroutine {
            if (userManager.getUID().isNotEmpty()) {
                db
                    .collection(userManager.getUID())
                    .document(DATE_DOCUMENT)
                    .get()
                    .addOnSuccessListener { result ->
                        if ((result.get(DATE_FIELD) as Number).toInt() <= date) {
                            it.resume(FirestoreDataNewerResult.Success(isNewer = false))
                        } else {
                            it.resume(FirestoreDataNewerResult.Success(isNewer = true))
                        }
                    }
                    .addOnFailureListener { e ->
                        it.resume(FirestoreDataNewerResult.Fail(exception = e.toString()))
                    }
            } else {
                it.resume(FirestoreDataNewerResult.Fail(exception = "not found user"))
            }
        }

    fun setFavourites(favourites: List<FirestoreFavouriteModel>) {
        if (userManager.getUID().isNotEmpty()) {
            val anilibriaFavourites = favourites.filter { it.category == com.example.common.constants.API_CATEGORY_ANILIBRIA }
            val kinopoiskFavourites = favourites.filter { it.category == com.example.common.constants.API_CATEGORY_KINOPOISK }

            for (i in anilibriaFavourites) {
                db
                    .collection(userManager.getUID())
                    .document(FAVOURITES_DOCUMENT)
                    .collection(com.example.common.constants.API_CATEGORY_ANILIBRIA)
                    .document(i.titleId.toString())
                    .set(hashMapOf(Pair("url", i.url)))
            }

            for (i in kinopoiskFavourites) {
                db
                    .collection(userManager.getUID())
                    .document(FAVOURITES_DOCUMENT)
                    .collection(com.example.common.constants.API_CATEGORY_KINOPOISK)
                    .document(i.titleId.toString())
                    .set(hashMapOf(Pair("url", i.url)))
            }
        }
    }

    fun setDateSync() {
        val date = hashMapOf(
            DATE_FIELD to Calendar.getInstance().dateWithTime()
        )
        db
            .collection(userManager.getUID())
            .document(DATE_DOCUMENT)
            .set(date)
    }

    suspend fun getFavouritesAnilibria(): List<FirestoreFavouriteModel> =
        suspendCancellableCoroutine {
            if (userManager.getUID().isNotEmpty()) {
                val anilibriaFavourites = arrayListOf<FirestoreFavouriteModel>()

                db
                    .collection(userManager.getUID())
                    .document(FAVOURITES_DOCUMENT)
                    .collection(com.example.common.constants.API_CATEGORY_ANILIBRIA)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            anilibriaFavourites.add(
                                FirestoreFavouriteModel(
                                    titleId = document.id.toInt(),
                                    url = document.get("url").toString(),
                                    category = com.example.common.constants.API_CATEGORY_ANILIBRIA
                                )
                            )
                        }
                        it.resume(anilibriaFavourites)
                    }
                    .addOnFailureListener { _ ->
                        it.resume(emptyList())
                    }
            } else {
                it.resume(emptyList())
            }
        }

    suspend fun getFavouritesKinopoisk(): List<FirestoreFavouriteModel> =
        suspendCancellableCoroutine {
            if (userManager.getUID().isNotEmpty()) {
                val kinopoiskFavourites = arrayListOf<FirestoreFavouriteModel>()

                db
                    .collection(userManager.getUID())
                    .document(FAVOURITES_DOCUMENT)
                    .collection(com.example.common.constants.API_CATEGORY_KINOPOISK)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            kinopoiskFavourites.add(
                                FirestoreFavouriteModel(
                                    titleId = document.id.toInt(),
                                    url = document.get("url").toString(),
                                    category = com.example.common.constants.API_CATEGORY_KINOPOISK
                                )
                            )
                        }
                        it.resume(kinopoiskFavourites)
                    }
                    .addOnFailureListener { _ ->
                        it.resume(emptyList())
                    }
            } else {
                it.resume(emptyList())
            }
        }

    suspend fun deleteAllDocumentsAnilibria(): Boolean = suspendCancellableCoroutine {
        db
            .collection(userManager.getUID())
            .document(FAVOURITES_DOCUMENT)
            .collection(com.example.common.constants.API_CATEGORY_ANILIBRIA)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    document.reference.delete()
                }
                it.resume(true)
            }
            .addOnFailureListener { _ ->
                it.resume(false)
            }
    }

    suspend fun deleteAllDocumentsKinopoisk(): Boolean = suspendCancellableCoroutine {
        db
            .collection(userManager.getUID())
            .document(FAVOURITES_DOCUMENT)
            .collection(com.example.common.constants.API_CATEGORY_KINOPOISK)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    document.reference.delete()
                }
                it.resume(true)
            }
            .addOnFailureListener { _ ->
                it.resume(false)
            }
    }
}
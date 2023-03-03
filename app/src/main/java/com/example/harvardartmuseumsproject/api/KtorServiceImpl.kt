package com.example.harvardartmuseumsproject.api

import com.example.harvardartmuseumsproject.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import timber.log.Timber

class KtorServiceImplementation(private val client: HttpClient) : KtorService {

    private val apiKey = "ed169f9e-e807-41ff-9da7-f44a69fd184e"

    private val apiURL = "https://api.harvardartmuseums.org"

    override suspend fun getGalleries(level: Int): Galleries? = handleAPIRequest {
        client.get("$apiURL/gallery?floor=$level&apikey=$apiKey").body()
    }

    override suspend fun getGallery(id: String): Gallery? = handleAPIRequest {
        client.get("$apiURL/gallery/$id?apikey=$apiKey").body()
    }

    override suspend fun getObjects(galleryId: String): ArtObjects? = handleAPIRequest {
        client.get("$apiURL/object?gallery=$galleryId&apikey=$apiKey").body()
    }

    override suspend fun getImages(imageId: String): FullSizeImage? = handleAPIRequest {
        client.get("$apiURL/image/$imageId?apikey=$apiKey").body()
    }

    private suspend fun <T> handleAPIRequest(block: suspend () -> T): T? {
        return try {
            block()
        } catch (e: Exception) {
            Timber.d("Error: ${e.stackTraceToString()}")
            null
        }
    }
}

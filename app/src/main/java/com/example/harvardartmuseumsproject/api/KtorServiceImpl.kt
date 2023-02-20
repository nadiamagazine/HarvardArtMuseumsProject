package com.example.harvardartmuseumsproject.api

import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Objects
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import timber.log.Timber

class KtorServiceImplementation(
    private val client: HttpClient
) : KtorService {

    override suspend fun getGalleries(level: Int): Galleries? {
        return try {
            client.get("https://api.harvardartmuseums.org/gallery?floor=$level&apikey=ed169f9e-e807-41ff-9da7-f44a69fd184e")
                .body<Galleries>()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            Timber.d("Error: ${e.stackTraceToString()}")
            null
        }
    }


    override suspend fun getGallery(id: String): Gallery? {
        return try {
            client.get("https://api.harvardartmuseums.org/gallery/$id?apikey=ed169f9e-e807-41ff-9da7-f44a69fd184e")
                .body<Gallery>()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            Timber.d("Error: ${e.stackTraceToString()}")
            null
        }
    }

    override suspend fun getObjects(galleryId: String): Objects? {
        return try {
            client.get("https://api.harvardartmuseums.org/object?gallery=$galleryId&apikey=ed169f9e-e807-41ff-9da7-f44a69fd184e")
                .body<Objects>()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Timber.d("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            Timber.d("Error: ${e.stackTraceToString()}")
            null
        }
    }
}

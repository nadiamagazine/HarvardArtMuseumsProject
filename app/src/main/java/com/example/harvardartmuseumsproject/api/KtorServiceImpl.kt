package com.example.harvardartmuseumsproject.api

import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Groups
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import timber.log.Timber

class KtorServiceImplementation(
    private val client: HttpClient
) : KtorService {

    override suspend fun getListOfGalleriesOnEachLevel(level: Int): Galleries? {
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


    override suspend fun getListOfGroupsOfEachGallery(galleryGroupName: String): Groups? {
        return try {
            client.get("https://api.harvardartmuseums.org/group/?q=filters.values.name:$galleryGroupName&apikey=ed169f9e-e807-41ff-9da7-f44a69fd184e")
                .body<Groups>()
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

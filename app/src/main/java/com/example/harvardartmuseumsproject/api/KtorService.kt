package com.example.harvardartmuseumsproject.api

import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Groups
import com.example.harvardartmuseumsproject.model.Objects
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface KtorService {

    suspend fun getGalleries(level: Int): Galleries?

    suspend fun getGallery(id: String): Gallery?

    suspend fun getObjects(galleryId: String): Objects?

    companion object {

        fun create(): KtorService {
            return KtorServiceImplementation(
                client = HttpClient(CIO) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                        )
                    }
                    install(HttpTimeout) {
                        requestTimeoutMillis = 50000
                    }
                }
            )
        }
    }
}


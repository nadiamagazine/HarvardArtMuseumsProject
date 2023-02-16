package com.example.harvardartmuseumsproject.api

import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Groups
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface KtorService {

    suspend fun getListOfGalleriesOnEachLevel(level: Int): Galleries?

    suspend fun getListOfGroupsOfEachGallery(id: String): Gallery?

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


package com.example.movopfy.features.home.data.repository

import com.example.common.mappers.kinopoisk.HORRORS_CATEGORY
import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.home.Kinopoisk
import com.example.movopfy.features.home.domain.models.KinopoiskItem
import com.example.network.kinopoisk.models.KinopoiskDocs
import com.example.network.kinopoisk.models.KinopoiskList
import com.example.network.kinopoisk.models.KinopoiskMoviePoster
import com.example.network.kinopoisk.service.KinopoiskService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import retrofit2.Response

class KinopoiskRepositoryImplTest {

    private val kinopoiskService = mock<com.example.network.kinopoisk.service.KinopoiskService>()
    private val kinopoiskDocsDao = mock<KinopoiskDocsDao>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = arrayListOf(
            Kinopoisk(
                id = 0,
                category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY,
                previewUrl = "nice"
            )
        )

        val repositoryImpl = KinopoiskRepositoryImpl(
            kinopoiskService = kinopoiskService,
            kinopoiskDocsDao = kinopoiskDocsDao
        )

        `when`(kinopoiskDocsDao.getKinopoiskDocsByCategory(category = anyString()))
            .thenReturn(localData)

        val actual = repositoryImpl.getList(page = 1, category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)

        val expected = localData.map {
            KinopoiskItem(
                id = it.id,
                previewUrl = it.previewUrl
            )
        }

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)
        verifyNoInteractions(kinopoiskService)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = com.example.network.kinopoisk.models.KinopoiskList(
            docs = null,
            total = null,
            limit = null,
            page = null,
            pages = null
        )

        val repositoryImpl = KinopoiskRepositoryImpl(
            kinopoiskService = kinopoiskService,
            kinopoiskDocsDao = kinopoiskDocsDao
        )

        `when`(kinopoiskDocsDao.getKinopoiskDocsByCategory(category = anyString()))
            .thenReturn(emptyList())
        `when`(kinopoiskService.getList(page = 1, category = "+ужасы")).thenReturn(
            Response.success(
                responseData
            )
        )

        val actual = repositoryImpl.getList(page = 1, category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)

        val expected = emptyList<KinopoiskItem>()

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)
        verify(kinopoiskService).getList(page = 1, category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnResponseData() = runBlocking {
        val responseData = com.example.network.kinopoisk.models.KinopoiskList(
            docs = arrayListOf(
                com.example.network.kinopoisk.models.KinopoiskDocs(
                    id = 1,
                    poster = com.example.network.kinopoisk.models.KinopoiskMoviePoster(
                        url = "",
                        previewUrl = ""
                    )
                )
            ),
            total = 40,
            limit = 10,
            page = 1,
            pages = 4
        )

        val repositoryImpl = KinopoiskRepositoryImpl(
            kinopoiskService = kinopoiskService,
            kinopoiskDocsDao = kinopoiskDocsDao
        )

        `when`(kinopoiskDocsDao.getKinopoiskDocsByCategory(category = anyString()))
            .thenReturn(emptyList())
        `when`(
            kinopoiskService.getList(
                page = 1,
                category = "+ужасы"
            )
        ).thenReturn(Response.success(responseData))

        val actual = repositoryImpl.getList(page = 1, category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)

        val expected = responseData.docs
            ?.filter { it.id != null && it.poster != null && it.poster?.previewUrl != null }
            ?.map {
                KinopoiskItem(
                    id = it.id ?: 0,
                    previewUrl = it.poster?.previewUrl ?: ""
                )
            }

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY)
        verify(kinopoiskService).getList(
            page = 1,
            category = com.example.common.mappers.kinopoisk.HORRORS_CATEGORY,
            limit = 20,
            selectFields = listOf("id", "poster")
        )

        assertEquals(expected, actual)
    }
}
package com.example.movopfy.features.home.data.repository

import com.example.movopfy.common.mappers.kinopoisk.HORRORS_CATEGORY
import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.home.Kinopoisk
import com.example.movopfy.features.home.domain.models.KinopoiskItems
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.network.kinopoisk.models.KinopoiskList
import com.example.movopfy.network.kinopoisk.models.KinopoiskMoviePoster
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
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

    private val kinopoiskService = mock<KinopoiskService>()
    private val kinopoiskDocsDao = mock<KinopoiskDocsDao>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = arrayListOf(
            Kinopoisk(
                id = 0,
                category = HORRORS_CATEGORY,
                previewUrl = "nice"
            )
        )

        val repositoryImpl = KinopoiskRepositoryImpl(
            kinopoiskService = kinopoiskService,
            kinopoiskDocsDao = kinopoiskDocsDao
        )

        `when`(kinopoiskDocsDao.getKinopoiskDocsByCategory(category = anyString()))
            .thenReturn(localData)

        val actual = repositoryImpl.getList(page = 1, category = HORRORS_CATEGORY)

        val expected = localData.map {
            KinopoiskItems(
                id = it.id,
                previewUrl = it.previewUrl
            )
        }

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = HORRORS_CATEGORY)
        verifyNoInteractions(kinopoiskService)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = KinopoiskList(
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

        val actual = repositoryImpl.getList(page = 1, category = HORRORS_CATEGORY)

        val expected = emptyList<KinopoiskItems>()

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = HORRORS_CATEGORY)
        verify(kinopoiskService).getList(page = 1, category = HORRORS_CATEGORY)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnResponseData() = runBlocking {
        val responseData = KinopoiskList(
            docs = arrayListOf(
                KinopoiskDocs(
                    id = 1,
                    poster = KinopoiskMoviePoster(url = "", previewUrl = "")
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

        val actual = repositoryImpl.getList(page = 1, category = HORRORS_CATEGORY)

        val expected = responseData.docs
            ?.filter { it.id != null && it.poster != null && it.poster?.previewUrl != null }
            ?.map {
                KinopoiskItems(
                    id = it.id ?: 0,
                    previewUrl = it.poster?.previewUrl ?: ""
                )
            }

        verify(kinopoiskDocsDao).getKinopoiskDocsByCategory(category = HORRORS_CATEGORY)
        verify(kinopoiskService).getList(
            page = 1,
            category = HORRORS_CATEGORY,
            limit = 20,
            selectFields = listOf("id", "poster")
        )

        assertEquals(expected, actual)
    }
}
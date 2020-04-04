package com.itis.group11801.fedotova.smartfasting.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.data.ResultWrapper
import com.itis.group11801.fedotova.smartfasting.data.local.service.NewsDao
import com.itis.group11801.fedotova.smartfasting.data.remote.mapper.mapNewsLocalToNews
import com.itis.group11801.fedotova.smartfasting.data.remote.mapper.mapNewsToNewsLocal
import com.itis.group11801.fedotova.smartfasting.data.remote.mapper.mapResponseResultToNews
import com.itis.group11801.fedotova.smartfasting.data.remote.model.ResultResponse
import com.itis.group11801.fedotova.smartfasting.data.remote.service.NewsApiService
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getNews(): LiveData<ResultWrapper<List<News>>> =

        liveData(Dispatchers.IO) {

            emit(ResultWrapper.loading<List<News>>())

            val source: LiveData<ResultWrapper<List<News>>> =
                newsDao.getNews().map { list ->
                    ResultWrapper.success(
                        list.map {
                            mapNewsLocalToNews(it)
                        }
                    )
                }
            emitSource(source)

            val result = fromResponseToResultWrapper(service.getNews())

            if (result.status == ResultWrapper.Status.SUCCESS) {
                newsDao.insert(result.data!!.map { mapNewsToNewsLocal(it) })

            } else if (result.status == ResultWrapper.Status.ERROR) {
                emit(ResultWrapper.error(result.message!!))
                emitSource(source)
            }

        }

    private fun fromResponseToResultWrapper(response: Response<ResultResponse>): ResultWrapper<List<News>> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val newsList = body.newsResponse!!.map {
                    mapResponseResultToNews(it)
                }
                return ResultWrapper.success(newsList)
            }
        }
        return ResultWrapper.error("${response.code()} ${response.message()}")
    }
}

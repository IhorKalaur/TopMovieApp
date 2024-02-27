package ihor.kalaur.topmovieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ihor.kalaur.topmovieapp.data.remote.TmdbApi
import ihor.kalaur.topmovieapp.util.Constants.API_KEY
import ihor.kalaur.topmovieapp.util.Constants.TMDB_API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTmdbApi(): TmdbApi {
        fun getHttpClientWithInterceptor(): OkHttpClient {

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url

                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build()

                    val requestBuilder = original.newBuilder()
                        .url(url)

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(logging)
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(TMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getHttpClientWithInterceptor())
            .build()
            .create(TmdbApi::class.java)
    }
}

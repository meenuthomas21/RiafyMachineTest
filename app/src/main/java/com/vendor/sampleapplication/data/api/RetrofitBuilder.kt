package com.vendor.sampleapplication.data.api

import com.alnaqel.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val BASE_URL = "http://fastingconsole.us-east-1.elasticbeanstalk.com/"

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()
        val client = okHttpClient
            .newBuilder()


        //Log.i("url_base34",base_url+","+web_interface);

        /*
         * this is for secure authentication
         *
         *
         * */
        val interceptor: Interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val builder = original.newBuilder()

                val request = builder.build()
                return chain.proceed(request)
            }
        }

        client.addInterceptor(interceptor)
        client.interceptors().add(loggingInterceptor)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()


        return retrofit



       /* return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        */

    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
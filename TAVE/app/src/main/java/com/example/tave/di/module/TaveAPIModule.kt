package com.example.tave.di.module

import android.content.Context
import com.example.data.api.TaveAPIService
import com.example.data.repositoryImpl.TaveAPIRepositoryImpl
import com.example.domain.repository.TaveAPIRepository
import com.example.tave.BuildConfig
import com.example.tave.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

/***
 * TAVE API Module
 *  - Retrofit, OkHttp3 Instance를 생성하고 Data Layer의 RepositoryImpl에 의존성을 주입합니다.
 *  - provideBaseURL() : Backend Server의 URL 을 제공합니다
 *  - provideSSLSocketFactory(@Application context: Context): SSLSocketFactory
 *    : TAVE API Https SSL 인증서를 등록합니다.
 *  - provideOKHttpClient(sslSocketFactory: SSLSocketFactory)
 *    : OkHttpClient 객체를 Retrofit에 제공합니다.
 *      이 과정에서 SSL 인증서를 OkHttp3 클라이언트에 등록합니다.
 *  - provideRetrofitClient(sslSocketFactory: SSLSocketFactory)
 *    : Retrofit 객체를 생성하여 서비스 합니다.
 *  - provideAPIService(retrofit: Retrofit): TaveAPIService
 *    : Retrofit 객체를 활용하여 TaveAPIService 를 등록합니다.
 *  - provideTaveApiRepoImpl(taveAPIService: TaveAPIService)
 *    : TaveApiRepoImpl에 TaveAPIService 의존성을 제공합니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object TaveAPIModule {
    @Provides
    @Singleton
    fun provideBaseURL(): String = BuildConfig.TAVE_API_URL

    @Provides
    @Singleton
    fun provideSSLSocketFactory(@ApplicationContext context: Context): SSLSocketFactory {
        try {
            val certFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
            val certAuthInputStream: InputStream = context.resources.openRawResource(R.raw.certificate)
            var certificationAuth: Certificate? = null

            try {
                certificationAuth = certFactory.generateCertificate(certAuthInputStream)
            } catch (e: CertificateException) {
                e.printStackTrace()
            } finally {
                certAuthInputStream.close()
            }

            val keyStoreType: String = KeyStore.getDefaultType()
            val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)

            keyStore.load(null, null)
            if (certificationAuth == null) {
                throw Exception()
            }
            keyStore.setCertificateEntry("TaveCA", certificationAuth)

            val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
            val tmFactory: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)

            tmFactory.init(keyStore)

            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmFactory.trustManagers, null)

            return sslContext.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw Exception()
    }


    @Provides
    @Singleton
    fun provideOKHttpClient(
        sslSocketFactory: SSLSocketFactory
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .sslSocketFactory(sslSocketFactory)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        taveAPIBaseURL: String,
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(taveAPIBaseURL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): TaveAPIService =
        retrofit.create(TaveAPIService::class.java)

    @Provides
    @Singleton
    fun provideTaveApiRepoImpl(taveAPIService: TaveAPIService): TaveAPIRepository =
        TaveAPIRepositoryImpl(taveAPIService)
}
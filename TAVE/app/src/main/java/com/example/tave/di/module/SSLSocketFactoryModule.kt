package com.example.tave.di.module

import android.content.Context
import com.example.tave.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

/**
 * SSL SocketFactory Module
 * - provideCertification(Application Context)
 *   : SSL 인증서을 읽어와 반환합니다.
 * - provideKeyStore(certification: Certification)
 *   : SSL 인증서를 통해 Keystore를 생성하고 반환합니다.
 * - provideTrustManagerFactory(keystore: KeyStore)
 *   : keystore를 통해 TrustManagerFactory 객체를 초기화 하고 반환합니다.
 * - provideSSLSocketFactory(trustManagerFactory: TrustManagerFactory)
 *   : trustManagerFactory를 통해 socketfactory를 생성하고 반환합니다.
 **/
@Module
@InstallIn(SingletonComponent::class)
object SSLSocketFactoryModule {
    @Provides
    @Singleton
    fun provideCertification(@ApplicationContext context: Context): Certificate? {
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

        return certificationAuth
    }

    @Provides
    @Singleton
    fun provideKeyStore(certification: Certificate?): KeyStore {
        val keyStoreType: String = KeyStore.getDefaultType()
        val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)

        keyStore.load(null, null)
        if (certification == null) {
            throw Exception()
        }
        keyStore.setCertificateEntry("TaveCA", certification)
        return keyStore
    }

    @Provides
    @Singleton
    fun provideTrustManagerFactory(keystore: KeyStore): TrustManagerFactory {
        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
        val tmFactory: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)

        tmFactory.init(keystore)
        return tmFactory
    }

    @Provides
    @Singleton
    fun provideSSLSocketFactory(trustManagerFactory: TrustManagerFactory): SSLSocketFactory {
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustManagerFactory.trustManagers, null)

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
}
package com.example.tave.di.module

import android.content.Context
import com.example.tave.R
import com.example.tave.common.Constants
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

@Module
@InstallIn(SingletonComponent::class)
object SSLSocketFactoryModule {
    @Provides
    @Singleton
    fun provideCertification(@ApplicationContext context: Context): Certificate? {
        val certFactory: CertificateFactory = CertificateFactory.getInstance(Constants.ACCESS_CERT_TYPE)
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
        keyStore.setCertificateEntry(Constants.ACCESS_CERT_ALIAS, certification)
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
            val sslContext: SSLContext = SSLContext.getInstance(Constants.SSL_PROTOCOL)
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
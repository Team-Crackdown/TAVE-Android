package com.example.tave.common

object Constants {
    const val TAVE_ADMIN: String = "TAVE 운영진"

    //URL REQUEST HEADER
    const val AUTHORIZATION_HEADER_TITLE: String = "authorization"
    const val SSE_HEADER_TITLE: String = "accept"
    const val SSE_HEADER_VALUE: String = "text/event-stream"

    //MODULE TEXT
    const val ACCESS_TOKEN_TITLE: String = "accessToken"
    const val ACCESS_CERT_ALIAS: String = "TaveCA"
    const val ACCESS_CERT_TYPE: String = "X.509"
    const val SSL_PROTOCOL: String = "TLS"
    const val SSE_ACK_FLAG: String = "connected!"

    //GLIDE IMAGE CONTENT DESCRIPTION
    const val IMAGE_LOAD_SUCCESS_CONTENT_DESC: String = "이미지 로드 성공"
    const val IMAGE_LOAD_FAILED_CONTENT_DESC: String = "이미지 로드 실패"

    //QR FORMATTER
    const val WHITE: Int = 0xFFFFFFFF.toInt()
    const val BLACK: Int = 0x00000000
    const val QR_WIDTH: Int = 512
    const val QR_HEIGHT: Int = 512

    //DATETIME FORMATTER
    const val SERVER_DATE_TIME_FORMAT: String = "yyyy-MM-dd'T'HH:mm"
    const val OUTPUT_DATE_TIME_FORMAT: String = "yyyy-MM-dd HH:mm"
    const val SCHEDULE_DATE_TIME_FORMAT: String = "yyyy-MM-dd"

    //SCHEDULE FORMATTER
    const val IS_SCHEDULE_EMPTY_TITLE: String = "아직 정해진 일정이 없습니다."
    const val IS_SCHEDULE_EMPTY_DAY: String = "???"
    const val IS_SCHEDULE_D_DAY: String = "DAY"
}
package com.s2w.asm.common.exception

enum class ExceptionType(
    val httpStatusType: HttpStatusType,
    val errorCode: String,
    val message: String,
) {
    // 400 bad request
    ERR_400_PARAMS_ERROR(HttpStatusType.BAD_REQUEST, "BR001", "잘못된 파라미터 입니다."),

    // 404 not found
    ERR_404_SEED_ID_NOT_FOUND(HttpStatusType.NOT_FOUND, "NF001", "등록된 SEED_ID 가 없습니다."),

    // 500 internal server error
    ERR_500_INTERNAL_SERVER(HttpStatusType.INTERNAL_SERVER, "IS500", "서버와 연결이 불안정 합니다. 잠시 후 재시도 하십시오."),
}

enum class HttpStatusType(
    val status: Int,
) {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER(500),
}

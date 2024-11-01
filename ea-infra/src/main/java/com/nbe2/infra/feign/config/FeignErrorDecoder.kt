package com.nbe2.infra.feign.config

import com.nbe2.infra.feign.exception.OtherServerBadRequestException
import com.nbe2.infra.feign.exception.OtherServerExpiredTokenException
import com.nbe2.infra.feign.exception.OtherServerForbiddenException
import com.nbe2.infra.feign.exception.OtherServerUnauthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw OtherServerUnauthorizedException
                403 -> throw OtherServerForbiddenException
                419 -> throw OtherServerExpiredTokenException
                else -> throw OtherServerBadRequestException
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}

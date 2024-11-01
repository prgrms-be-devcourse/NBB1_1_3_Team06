package com.nbe2.api.global.aop

import jakarta.servlet.http.HttpServletRequest
import java.net.URLDecoder
import kotlin.collections.HashMap
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LogAspect {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Pointcut(
            "(execution(* com.nbe2.domain..*(..)) || execution(* com.nbe2.infra..*(..))) &&" +
                    " !execution(* com.nbe2.infra.feign..*(..)) && !execution(* com.nbe2.common..*(..))"
    )
    fun all() {}

    @Pointcut(
            "execution(* com.nbe2.api..*Api.*(..)) && !execution(* com.nbe2.api.HealthCheckApi..*(..))"
    )
    fun controller() {}

    @Around("all()")
    @Throws(Throwable::class)
    fun logging(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        return try {
            joinPoint.proceed()
        } finally {
            val end = System.currentTimeMillis()
            val timeInMs = end - start
            log.info("{} | time = {}ms", joinPoint.signature, timeInMs)
        }
    }

    @Around("controller()")
    @Throws(Throwable::class)
    fun loggingBefore(joinPoint: ProceedingJoinPoint): Any? {
        val request =
                (RequestContextHolder.getRequestAttributes()
                                as? ServletRequestAttributes)
                        ?.request
                        ?: throw IllegalStateException("No request found")

        val controllerName = joinPoint.signature.declaringType.name
        val methodName = joinPoint.signature.name
        val params = HashMap<String, Any>()

        try {
            val decodedURI = URLDecoder.decode(request.requestURI, "UTF-8")

            params["controller"] = controllerName
            params["method"] = methodName
            params["params"] = getParams(request)
            params["log_time"] = System.currentTimeMillis()
            params["request_uri"] = decodedURI
            params["http_method"] = request.method
        } catch (e: Exception) {
            log.error("LoggerAspect error", e)
        }

        log.info("[{}] {}", params["http_method"], params["request_uri"])
        log.info("method: {}.{}", params["controller"], params["method"])
        log.info("params: {}", params["params"])

        return joinPoint.proceed()
    }

    private fun getParams(request: HttpServletRequest): JSONObject {
        val jsonObject = JSONObject()
        val params = request.parameterNames
        while (params.hasMoreElements()) {
            val param = params.nextElement()
            val replaceParam = param.replace(".", "-")
            jsonObject.put(replaceParam, request.getParameter(param))
        }
        return jsonObject
    }
}

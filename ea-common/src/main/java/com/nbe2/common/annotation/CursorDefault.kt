package com.nbe2.common.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class CursorDefault(val cursor: Long = Long.MAX_VALUE, val size: Int = 10)

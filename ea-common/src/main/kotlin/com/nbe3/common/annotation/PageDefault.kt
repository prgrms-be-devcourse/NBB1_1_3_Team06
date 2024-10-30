package com.nbe3.common.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class PageDefault(val page: Int = 0, val size: Int = 10)

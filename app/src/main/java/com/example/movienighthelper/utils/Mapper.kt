package com.example.movienighthelper.utils

interface ConvertibleTo<T,R> {
    fun toEntity(): T
    fun toUiModel(): R
}
fun <T, R, U : ConvertibleTo<T, R>> ArrayList<U>.toEntityList(): ArrayList<T> {
    return ArrayList<T>(this.map { it.toEntity() })
}
fun <T, R, U : ConvertibleTo<T, R>> ArrayList<U>.toUiModelList(): ArrayList<R> {
    return ArrayList<R>(this.map { it.toUiModel() })
}
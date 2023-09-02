package com.example.movienighthelper.utils

interface ConvertibleTo<R> {
    fun toUiModel(): R
}
fun <R> List<out ConvertibleTo<R>>.toUiModelList(): List<R> {
    return this.map { it.toUiModel() }
}
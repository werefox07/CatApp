package com.werefox.core_domain.mapper

interface Mapper<InputType, ReturnType> {

    fun map(obj: InputType): ReturnType
}
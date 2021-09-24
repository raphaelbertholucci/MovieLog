package com.bertholucci.core.base

interface BaseMapper<MODEL, DOMAIN> {

    fun mapFromDomain(domain: DOMAIN): MODEL

    fun mapToDomain(model: MODEL): DOMAIN
}
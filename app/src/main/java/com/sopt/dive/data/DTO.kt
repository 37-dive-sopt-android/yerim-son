package com.sopt.dive.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserListDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("data")
    val data: List<ResponseUserDataDto>,
    @SerialName("support")
    val support: ResponseSupportDto,
    @SerialName("_meta")
    val meta: ResponseMetaDto
)

@Serializable
data class ResponseUserDataDto(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("avatar")
    val avatar: String
)

@Serializable
data class ResponseSupportDto(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)

@Serializable
data class ResponseMetaDto(
    @SerialName("powered_by")
    val poweredBy: String,
    @SerialName("upgrade_url")
    val upgradeUrl: String,
    @SerialName("docs_url")
    val docsUrl: String,
    @SerialName("template_gallery")
    val templateGallery: String,
    @SerialName("message")
    val message: String,
    @SerialName("features")
    val features: List<String>,
    @SerialName("upgrade_cta")
    val upgradeCta: String
)


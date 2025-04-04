package com.msl.lookheart.model

data class DialogState(
    val isVisible: Boolean = false,
    val titleResId: Int? = null,

    val messageResId: Int? = null,
    val message: String? = null,

    val confirmButtonResId: Int? = null,
    val cancelButtonResId: Int? = null,

    val dismissEnable: Boolean = true,
    val onConfirm: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null
)

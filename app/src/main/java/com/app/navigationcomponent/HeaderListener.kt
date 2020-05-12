package com.app.navigationcomponent

import android.opengl.Visibility

interface HeaderListener {
    fun setTitle(title: String)
    fun setSubTitle(subTitle: String)
    fun isSubTitleVisible(isVisible: Boolean)
}
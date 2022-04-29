package com.tinydavid.snoocodewatchface.utils

import android.content.Context
import androidx.wear.watchface.style.UserStyleSchema
import androidx.wear.watchface.style.UserStyleSetting
import androidx.wear.watchface.style.WatchFaceLayer
import com.tinydavid.snoocodewatchface.R
import com.tinydavid.snoocodewatchface.domain.model.DRAW_HOUR_PIPS_DEFAULT

// Keys to matched content in the  the user style settings. We listen for changes to these
// values in the renderer and if new, we will update the database and update the watch face
// being rendered.
const val COLOR_STYLE_SETTING = "color_style_setting"
const val DRAW_HOUR_PIPS_STYLE_SETTING = "draw_hour_pips_style_setting"
const val WATCH_HAND_LENGTH_STYLE_SETTING = "watch_hand_length_style_setting"

fun myUserStyleSchema(context: Context): UserStyleSchema {

    val drawHourPipsStyleSetting = UserStyleSetting.BooleanUserStyleSetting(
        UserStyleSetting.Id(""),
        context.resources,
        R.string.watchface_pips_setting,
        R.string.watchface_pips_setting_description,
        null,
        listOf(WatchFaceLayer.BASE),
        DRAW_HOUR_PIPS_DEFAULT
    )

    return UserStyleSchema(listOf(drawHourPipsStyleSetting))
}
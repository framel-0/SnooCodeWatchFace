package com.tinydavid.snoocodewatchface

import android.util.Log
import android.view.SurfaceHolder
import androidx.wear.watchface.*
import androidx.wear.watchface.style.CurrentUserStyleRepository
import androidx.wear.watchface.style.UserStyleSchema
import com.tinydavid.snoocodewatchface.utils.complicationSlotsManagerUtil
import com.tinydavid.snoocodewatchface.utils.myUserStyleSchema

class SAnalogWatchFaceService : WatchFaceService() {

    override fun createUserStyleSchema(): UserStyleSchema =
        myUserStyleSchema(context = applicationContext)

    override fun createComplicationSlotsManager(
        currentUserStyleRepository: CurrentUserStyleRepository
    ): ComplicationSlotsManager = complicationSlotsManagerUtil(
        context = applicationContext,
        currentUserStyleRepository = currentUserStyleRepository
    )


    override suspend fun createWatchFace(
        surfaceHolder: SurfaceHolder,
        watchState: WatchState,
        complicationSlotsManager: ComplicationSlotsManager,
        currentUserStyleRepository: CurrentUserStyleRepository
    ): WatchFace {
        Log.d(TAG, "createWatchFace()")

        // Creates class that renders the watch face.
        val renderer = AnalogWatchCanvasRenderer(
            context = applicationContext,
            surfaceHolder = surfaceHolder,
            watchState = watchState,
            complicationSlotsManager = complicationSlotsManager,
            currentUserStyleRepository = currentUserStyleRepository,
            canvasType = CanvasType.SOFTWARE
        )

        // Creates the watch face.
        return WatchFace(
            watchFaceType = WatchFaceType.ANALOG,
            renderer = renderer
        )
    }


    companion object {
        const val TAG = "AnalogWatchFaceService"
    }
}

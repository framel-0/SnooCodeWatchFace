package com.tinydavid.snoocodewatchface.utils

import android.content.Context
import android.graphics.RectF
import androidx.wear.watchface.CanvasComplicationFactory
import androidx.wear.watchface.ComplicationSlot
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.complications.ComplicationSlotBounds
import androidx.wear.watchface.complications.DefaultComplicationDataSourcePolicy
import androidx.wear.watchface.complications.SystemDataSources
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.rendering.CanvasComplicationDrawable
import androidx.wear.watchface.complications.rendering.ComplicationDrawable
import androidx.wear.watchface.style.CurrentUserStyleRepository
import com.tinydavid.snoocodewatchface.R

// Information needed for complications.
// Creates bounds for the locations of both right and left complications. (This is the
// location from 0.0 - 1.0.)
// Both left and right complications use the same top and bottom bounds.
private const val TOP_AND_BOTTOM_COMPLICATION_LEFT_BOUND = 0.4f
private const val TOP_AND_BOTTOM_COMPLICATION_RIGHT_BOUND = 0.6f

private const val LEFT_AND_RIGHT_COMPLICATIONS_TOP_BOUND = 0.4f
private const val LEFT_AND_RIGHT_COMPLICATIONS_BOTTOM_BOUND = 0.6f

private const val LEFT_COMPLICATION_LEFT_BOUND = 0.15f
private const val LEFT_COMPLICATION_RIGHT_BOUND = 0.35f

private const val TOP_COMPLICATION_TOP_BOUND = 0.15f
private const val TOP_COMPLICATION_BOTTOM_BOUND = 0.35f

private const val RIGHT_COMPLICATION_LEFT_BOUND = 0.65f
private const val RIGHT_COMPLICATION_RIGHT_BOUND = 0.85f

private const val BOTTOM_COMPLICATION_TOP_BOUND = 0.65f
private const val BOTTOM_COMPLICATION_BOTTOM_BOUND = 0.85f


private const val DEFAULT_COMPLICATION_STYLE_DRAWABLE_ID = R.drawable.complication_white_style

// Unique IDs for each complication. The settings activity that supports allowing users
// to select their complication data provider requires numbers to be >= 0.
internal const val LEFT_COMPLICATION_ID = 100
internal const val TOP_COMPLICATION_ID = 101
internal const val RIGHT_COMPLICATION_ID = 102
internal const val BOTTOM_COMPLICATION_ID = 103

/**
 * Represents the unique id associated with a complication and the complication types it supports.
 */
sealed class ComplicationConfig(val id: Int, val supportedTypes: List<ComplicationType>) {
    object Left : ComplicationConfig(
        LEFT_COMPLICATION_ID,
        listOf(
            ComplicationType.RANGED_VALUE,
            ComplicationType.MONOCHROMATIC_IMAGE,
            ComplicationType.SHORT_TEXT,
            ComplicationType.SMALL_IMAGE
        )
    )

    object Top : ComplicationConfig(
        TOP_COMPLICATION_ID,
        listOf(
            ComplicationType.RANGED_VALUE,
            ComplicationType.MONOCHROMATIC_IMAGE,
            ComplicationType.SHORT_TEXT,
            ComplicationType.SMALL_IMAGE
        )
    )

    object Right : ComplicationConfig(
        RIGHT_COMPLICATION_ID,
        listOf(
            ComplicationType.RANGED_VALUE,
            ComplicationType.MONOCHROMATIC_IMAGE,
            ComplicationType.SHORT_TEXT,
            ComplicationType.SMALL_IMAGE
        )
    )

    object Bottom : ComplicationConfig(
        BOTTOM_COMPLICATION_ID,
        listOf(
            ComplicationType.RANGED_VALUE,
            ComplicationType.MONOCHROMATIC_IMAGE,
            ComplicationType.SHORT_TEXT,
            ComplicationType.SMALL_IMAGE
        )
    )
}

fun complicationSlotsManagerUtil(
    context: Context,
    currentUserStyleRepository: CurrentUserStyleRepository,
    drawableId: Int = DEFAULT_COMPLICATION_STYLE_DRAWABLE_ID
): ComplicationSlotsManager {

    val defaultCanvasComplicationFactory = CanvasComplicationFactory { watchState, callback ->
        CanvasComplicationDrawable(
            ComplicationDrawable.getDrawable(context, drawableId)!!,
            watchState,
            callback
        )
    }

    val leftComplicationSlot = ComplicationSlot.createRoundRectComplicationSlotBuilder(
        id = ComplicationConfig.Left.id,
        canvasComplicationFactory = defaultCanvasComplicationFactory,
        supportedTypes = ComplicationConfig.Left.supportedTypes,
        defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
            SystemDataSources.DATA_SOURCE_DATE
        ),
        bounds = ComplicationSlotBounds(
            RectF(
                LEFT_COMPLICATION_LEFT_BOUND,
                LEFT_AND_RIGHT_COMPLICATIONS_TOP_BOUND,
                LEFT_COMPLICATION_RIGHT_BOUND,
                LEFT_AND_RIGHT_COMPLICATIONS_BOTTOM_BOUND
            )
        )
    ).setDefaultDataSourceType(ComplicationType.SHORT_TEXT).build()

    val rightComplicationSlot = ComplicationSlot.createRoundRectComplicationSlotBuilder(
        id = ComplicationConfig.Right.id,
        canvasComplicationFactory = defaultCanvasComplicationFactory,
        supportedTypes = ComplicationConfig.Right.supportedTypes,
        defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
            SystemDataSources.NO_DATA_SOURCE
        ),
        bounds = ComplicationSlotBounds(
            RectF(
                RIGHT_COMPLICATION_LEFT_BOUND,
                LEFT_AND_RIGHT_COMPLICATIONS_TOP_BOUND,
                RIGHT_COMPLICATION_RIGHT_BOUND,
                LEFT_AND_RIGHT_COMPLICATIONS_BOTTOM_BOUND
            )
        )
    ).setDefaultDataSourceType(ComplicationType.SHORT_TEXT)
        .build()

    val topComplicationSlot = ComplicationSlot.createRoundRectComplicationSlotBuilder(
        id = ComplicationConfig.Top.id,
        canvasComplicationFactory = defaultCanvasComplicationFactory,
        supportedTypes = ComplicationConfig.Right.supportedTypes,
        defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
            SystemDataSources.DATA_SOURCE_STEP_COUNT
        ),
        bounds = ComplicationSlotBounds(
            RectF(
                TOP_AND_BOTTOM_COMPLICATION_LEFT_BOUND,
                TOP_COMPLICATION_TOP_BOUND,
                TOP_AND_BOTTOM_COMPLICATION_RIGHT_BOUND,
                TOP_COMPLICATION_BOTTOM_BOUND
            )
        )
    ).setDefaultDataSourceType(ComplicationType.SHORT_TEXT)
        .build()

    val bottomComplicationSlot = ComplicationSlot.createRoundRectComplicationSlotBuilder(
        id = ComplicationConfig.Bottom.id,
        canvasComplicationFactory = defaultCanvasComplicationFactory,
        supportedTypes = ComplicationConfig.Right.supportedTypes,
        defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
            SystemDataSources.DATA_SOURCE_STEP_COUNT
        ),
        bounds = ComplicationSlotBounds(
            RectF(
                TOP_AND_BOTTOM_COMPLICATION_LEFT_BOUND,
                BOTTOM_COMPLICATION_TOP_BOUND,
                TOP_AND_BOTTOM_COMPLICATION_RIGHT_BOUND,
                BOTTOM_COMPLICATION_BOTTOM_BOUND
            )
        )
    ).setDefaultDataSourceType(ComplicationType.SHORT_TEXT)
        .build()


    return ComplicationSlotsManager(
        listOf(
            leftComplicationSlot,
            topComplicationSlot,
            rightComplicationSlot,
            bottomComplicationSlot
        ),
        currentUserStyleRepository
    )


}
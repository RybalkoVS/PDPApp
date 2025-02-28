package com.example.pdpapp.core.theme_core.dimens

import androidx.compose.ui.unit.dp

object SystemDimens {

    val padding: Dimens.Padding = Dimens.Padding()
    val size: Dimens.Size = Dimens.Size()
    val radius: Dimens.Radius = Dimens.Radius()
    val weight: Dimens.Weight = Dimens.Weight()
}

sealed interface Dimens {

    class Padding : Dimens {
        val padding0dp = 0.dp
        val padding2dp = 2.dp
        val padding4dp = 4.dp
        val padding8dp = 8.dp
        val padding16dp = 16.dp
        val padding20dp = 20.dp
        val padding32dp = 32.dp
    }

    class Size : Dimens {
        val size2dp = 2.dp
        val size4dp = 4.dp
        val size8dp = 8.dp
        val size64dp = 64.dp
        val size150dp = 150.dp
        val size200dp = 200.dp
    }

    class Radius : Dimens {
        val radius0dp = 0.dp
        val radius2dp = 2.dp
        val radius4dp = 4.dp
        val radius8dp = 8.dp
        val radius16dp = 16.dp
    }

    class Weight: Dimens {
        val weight25 = 0.25f
        val weight50 = 0.5f
        val weight100 = 1f
    }
}
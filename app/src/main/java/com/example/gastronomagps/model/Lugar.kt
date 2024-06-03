package com.example.gastronomagps.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Lugar(@StringRes val titulo: Int,
            @DrawableRes val imageResourceId: Int,
            @StringRes val ubicacion: Int,
            @StringRes val descripcion: Int,
            @StringRes val horario: Int,
            @StringRes val telefono: Int)


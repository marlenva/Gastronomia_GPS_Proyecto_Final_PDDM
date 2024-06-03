package com.example.gastronomagps.data

import com.example.gastronomagps.R
import com.example.gastronomagps.model.Lugar

/**
 * Clase que proporciona datos estáticos para diferentes tipos de lugares.
 */
class Datasource() {
    /**
     * Retorna una lista estática de lugares de tacos.
     *
     * @return Lista de lugares de tacos.
     */
    fun tacos(): List<Lugar> {
        return listOf<Lugar>(
            Lugar(R.string.tituloT1, R.drawable.valadez, R.string.ubiT1, R.string.descT1, R.string.horarioT1, R.string.telefonoT1),
            Lugar(R.string.tituloT2, R.drawable.bere, R.string.ubiT2, R.string.descT2, R.string.horarioT2, R.string.telefonoT2),
            Lugar(R.string.tituloT3, R.drawable.nolo, R.string.ubiT3, R.string.descT3, R.string.horarioT3, R.string.telefonoT3),
            Lugar(R.string.tituloT4, R.drawable.carnitas, R.string.ubiT4, R.string.descT4, R.string.horarioT4, R.string.telefonoT4),
            Lugar(R.string.tituloT5, R.drawable.rinconcito, R.string.ubiT5, R.string.descT5, R.string.horarioT5, R.string.telefonoT5)
        )
    }

    /**
     * Retorna una lista estática de lugares de gorditas.
     *
     * @return Lista de lugares de gorditas.
     */
    fun gorditas(): List<Lugar> {
        return listOf<Lugar>(
            Lugar(R.string.tituloG1, R.drawable.gor, R.string.ubiG1, R.string.descG1, R.string.horarioG1, R.string.telefonoG1),
            Lugar(R.string.tituloG2, R.drawable.coco, R.string.ubiG2, R.string.descG2, R.string.horarioG2, R.string.telefonoG2),
        )
    }
    /**
     * Retorna una lista estática de lugares de mariscos.
     *
     * @return Lista de lugares de mariscos.
     */
    fun mariscos(): List<Lugar> {
        return listOf<Lugar>(
            Lugar(R.string.tituloM1, R.drawable.masky, R.string.ubiM1, R.string.descM1, R.string.horarioM1, R.string.telefonoM1),
            Lugar(R.string.tituloM2, R.drawable.rey, R.string.ubiM2, R.string.descM2, R.string.horarioM2, R.string.telefonoM2),
            )
    }

    /**
     * Retorna una lista estática de otros lugares.
     *
     * @return Lista de otros lugares.
     */
    fun otros(): List<Lugar> {
        return listOf<Lugar>(
            Lugar(R.string.tituloO1, R.drawable._1, R.string.ubiO1, R.string.descO1, R.string.horarioO1, R.string.telefonoO1),
            Lugar(R.string.tituloO2, R.drawable.coffe, R.string.ubiO2, R.string.descO2, R.string.horarioO2, R.string.telefonoO2),
            Lugar(R.string.tituloO3, R.drawable.raspados, R.string.ubiO3, R.string.descO3, R.string.horarioO3, R.string.telefonoO3),
            Lugar(R.string.tituloO4, R.drawable.eva, R.string.ubiO4, R.string.descO4, R.string.horarioO4, R.string.telefonoO4),
            Lugar(R.string.tituloO5, R.drawable.lonch, R.string.ubiO5, R.string.descO5, R.string.horarioO5, R.string.telefonoO5)
        )
    }
}
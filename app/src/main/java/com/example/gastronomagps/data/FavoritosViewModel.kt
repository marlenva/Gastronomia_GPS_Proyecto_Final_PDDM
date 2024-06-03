package com.example.gastronomagps.data

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.gastronomagps.model.Lugar

/**
 * ViewModel para gestionar los lugares favoritos en la aplicación.
 */
class FavoritosViewModel : ViewModel() {
    // Lista mutable de lugares favoritos.
    val favoritos = mutableStateListOf<Lugar>()

    /**
     * Agrega un lugar a la lista de favoritos si no está ya en la lista.
     *
     * @param lugar El lugar a agregar a la lista de favoritos.
     */
    fun agregarFavoritos(lugar: Lugar) {
        if (!favoritos.contains(lugar)) {
            favoritos.add(lugar)
        }
    }

    /**
     * Elimina un lugar de la lista de favoritos.
     *
     * @param lugar El lugar a eliminar de la lista de favoritos.
     */
    fun eliminarFavoritos(lugar: Lugar) {
        favoritos.remove(lugar)
    }

    /**
     * Verifica si un lugar está en la lista de favoritos.
     *
     * @param lugar El lugar a verificar.
     * @return true si el lugar está en la lista de favoritos, false en caso contrario.
     */
    fun isFavorite(lugar: Lugar): Boolean {
        return favoritos.contains(lugar)
    }

    /**
     * Agrega o elimina un lugar de la lista de favoritos dependiendo de su estado actual.
     *
     * @param lugar El lugar para el cual cambiar el estado de favorito.
     */
    fun toggleFavorite(lugar: Lugar) {
        if (favoritos.contains(lugar)) {
            favoritos.remove(lugar)
        } else {
            favoritos.add(lugar)
        }
    }
}

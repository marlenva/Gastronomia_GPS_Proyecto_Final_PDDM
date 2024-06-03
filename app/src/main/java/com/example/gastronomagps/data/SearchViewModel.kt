package com.example.gastronomagps.data

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.gastronomagps.R
import com.example.gastronomagps.model.Lugar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel para gestionar la búsqueda de lugares en la aplicación.
 */
class SearchViewModel : ViewModel() {
    // Flujo mutable que representa la consulta de búsqueda actual
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    // Lista de todos los lugares disponibles en la aplicación.
    private val allLugares = listOf(
        Lugar(R.string.tituloT1, R.drawable.valadez, R.string.ubiT1, R.string.descT1, R.string.horarioT1, R.string.telefonoT1),
        Lugar(R.string.tituloT2, R.drawable.bere, R.string.ubiT2, R.string.descT2, R.string.horarioT2, R.string.telefonoT2),
        Lugar(R.string.tituloT3, R.drawable.nolo, R.string.ubiT3, R.string.descT3, R.string.horarioT3, R.string.telefonoT3),
        Lugar(R.string.tituloT4, R.drawable.carnitas, R.string.ubiT4, R.string.descT4, R.string.horarioT4, R.string.telefonoT4),
        Lugar(R.string.tituloT5, R.drawable.rinconcito, R.string.ubiT5, R.string.descT5, R.string.horarioT5, R.string.telefonoT5),
        Lugar(R.string.tituloG1, R.drawable.gor, R.string.ubiG1, R.string.descG1, R.string.horarioG1, R.string.telefonoG1),
        Lugar(R.string.tituloG2, R.drawable.coco, R.string.ubiG2, R.string.descG2, R.string.horarioG2, R.string.telefonoG2),
        Lugar(R.string.tituloM1, R.drawable.masky, R.string.ubiM1, R.string.descM1, R.string.horarioM1, R.string.telefonoM1),
        Lugar(R.string.tituloM2, R.drawable.rey, R.string.ubiM2, R.string.descM2, R.string.horarioM2, R.string.telefonoM2),
        Lugar(R.string.tituloO1, R.drawable._1, R.string.ubiO1, R.string.descO1, R.string.horarioO1, R.string.telefonoO1),
        Lugar(R.string.tituloO2, R.drawable.coffe, R.string.ubiO2, R.string.descO2, R.string.horarioO2, R.string.telefonoO2),
        Lugar(R.string.tituloO3, R.drawable.raspados, R.string.ubiO3, R.string.descO3, R.string.horarioO3, R.string.telefonoO3),
        Lugar(R.string.tituloO4, R.drawable.eva, R.string.ubiO4, R.string.descO4, R.string.horarioO4, R.string.telefonoO4),
        Lugar(R.string.tituloO5, R.drawable.lonch, R.string.ubiO5, R.string.descO5, R.string.horarioO5, R.string.telefonoO5)
    )
    // Flujo mutable que representa los resultados de la búsqueda actual.
    private val _resultados = MutableStateFlow(allLugares)
    val resultados: StateFlow<List<Lugar>> get() = _resultados

    /**
     * Actualiza la consulta de búsqueda y los resultados según la nueva consulta.
     *
     * @param query La nueva consulta de búsqueda.
     * @param context El contexto de la aplicación para obtener los recursos de cadena.
     */
    fun onQueryChanged(query: String, context: Context) {
        _query.value = query
        _resultados.value = if (query.isEmpty()) {
            allLugares // Si la consulta está vacía, se muestran todos los lugares.
        } else {
            // Filtrar los lugares según la consulta de búsqueda.
            allLugares.filter { lugar ->
                val titulo = context.getString(lugar.titulo)
                val ubicacion = context.getString(lugar.ubicacion)
                val descripcion = context.getString(lugar.descripcion)
                val horario = context.getString(lugar.horario)
                val telefono = context.getString(lugar.telefono)
                // Se devuelve true si algún campo del lugar contiene la consulta, ignorando mayúsculas y minúsculas.
                titulo.contains(query, ignoreCase = true) ||
                        ubicacion.contains(query, ignoreCase = true) ||
                        descripcion.contains(query, ignoreCase = true) ||
                        horario.contains(query, ignoreCase = true) ||
                        telefono.contains(query, ignoreCase = true)
            }
        }
    }
}

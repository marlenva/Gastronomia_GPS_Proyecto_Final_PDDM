package com.example.gastronomagps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gastronomagps.data.Datasource
import com.example.gastronomagps.data.FavoritosViewModel
import com.example.gastronomagps.model.Lugar
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Otros(navController: NavController, favoritosViewModel: FavoritosViewModel) {
    // Estado para mantener los favoritos
    var favorites by remember { mutableStateOf(setOf<Lugar>()) }

    // Configuración del Scaffold que incluye una TopAppBar y una BottomAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("menu") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, // Icono de regreso
                            contentDescription = "Back to Menu"
                        )
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.sin_fondo), // Imagen pequeña
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF000080), // Color azul marino
                    titleContentColor = Color.White, // Color del título blanco
                    navigationIconContentColor = Color.White, // Color del icono de navegación blanco
                    actionIconContentColor = Color.White // Color del icono de acción blanco
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.height(65.dp),
                containerColor = Color(0xFF000080) // Color azul marino
            ) {
                // Icono para navegar a la pantalla de búsqueda
                IconButton(onClick = { navController.navigate("buscar")}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "buscar",
                        tint = Color.White, // Color blanco para el icono
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espaciador para alinear los iconos
                // Icono para navegar a la pantalla principal
                IconButton(onClick = { navController.navigate("gpsApp")}) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "principal",
                        tint = Color.White, // Color blanco para el icono
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espaciador para alinear los iconos
                // Icono para navegar a la pantalla de favoritos
                IconButton(onClick = { navController.navigate("favoritos")}) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "favoritos",
                        tint = Color.White, // Color blanco para el icono
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        // Contenido principal de la pantalla dentro de una columna
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Título de la sección "Otros"
            Text(
                text = stringResource(R.string.otros),
                style = MaterialTheme.typography.labelLarge
            )
            // Lista de lugares dentro de la categoría "Otros"
            LazyColumn {
                items(Datasource().otros()) { otros ->
                    LugaresCard(
                        navController = navController,
                        lugar = otros,
                        isFavorite = favoritosViewModel.isFavorite(otros),
                        onFavoriteClick = { favoritosViewModel.agregarFavoritos(it) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

/**
 * Función de vista previa para la pantalla de "Otros".
 */
@Preview(showBackground = true)
@Composable
fun OtrosPreview() {
    GastronomíaGPSTheme {
        Otros(navController = NavController(LocalContext.current), favoritosViewModel = FavoritosViewModel())
    }
}
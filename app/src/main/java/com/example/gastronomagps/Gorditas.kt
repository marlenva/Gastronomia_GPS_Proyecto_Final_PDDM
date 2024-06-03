package com.example.gastronomagps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gastronomagps.R
import com.example.gastronomagps.data.Datasource
import com.example.gastronomagps.data.FavoritosViewModel
import com.example.gastronomagps.model.Lugar
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Gorditas(navController: NavController, favoritosViewModel: FavoritosViewModel) {
    Scaffold(
        topBar = {
            // Barra superior de la pantalla
            TopAppBar(
                title = {
                    // Título de la barra superior
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    // Icono de navegación para volver al menú
                    IconButton(onClick = { navController.navigate("menu") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver al menú"
                        )
                    }
                },
                actions = {
                    // Imagen pequeña en la barra superior
                    Image(
                        painter = painterResource(id = R.drawable.sin_fondo),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF000080), // Color de fondo azul marino
                    titleContentColor = Color.White, // Color del título en blanco
                    navigationIconContentColor = Color.White, // Color del icono de navegación en blanco
                    actionIconContentColor = Color.White // Color del icono de acción en blanco
                )
            )
        },
        bottomBar = {
            // Barra inferior de la pantalla
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.height(65.dp),
                containerColor = Color(0xFF000080)
            ) {
                // Botón de búsqueda en la barra inferior
                IconButton(onClick = { navController.navigate("buscar") }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espaciador para distribuir los botones uniformemente
                // Botón de inicio en la barra inferior
                IconButton(onClick = { navController.navigate("gpsApp") }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Principal",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Otro espaciador
                // Botón de favoritos en la barra inferior
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favoritos",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        // Contenido principal de la pantalla
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Título de la sección de Gorditas
            Text(
                text = stringResource(R.string.gorditas),
                style = MaterialTheme.typography.labelLarge
            )
            // Lista de Gorditas
            LazyColumn {
                items(Datasource().gorditas()) { gorditas ->
                    LugaresCard(
                        navController = navController,
                        lugar = gorditas,
                        isFavorite = favoritosViewModel.isFavorite(gorditas),
                        onFavoriteClick = { favoritosViewModel.agregarFavoritos(it) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

/**
 * Función de vista previa para la pantalla de Gorditas.
 */
@Preview(showBackground = true)
@Composable
fun GorditasPreview() {
    GastronomíaGPSTheme {
        Gorditas(navController = NavController(LocalContext.current), favoritosViewModel = FavoritosViewModel())
    }
}
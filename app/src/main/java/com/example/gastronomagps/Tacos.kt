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
import com.example.gastronomagps.data.Datasource
import com.example.gastronomagps.data.FavoritosViewModel
import com.example.gastronomagps.model.Lugar
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tacos(navController: NavController, favoritosViewModel: FavoritosViewModel) {
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
                IconButton(onClick = { navController.navigate("buscar") }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "buscar",
                        tint = Color.White, // Color blanco para el icono
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espaciador para alinear los iconos
                // Icono para navegar a la pantalla principal
                IconButton(onClick = { navController.navigate("gpsApp") }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "principal",
                        tint = Color.White, // Color blanco para el icono
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espaciador para alinear los iconos
                // Icono para navegar a la pantalla de favoritos
                IconButton(onClick = { navController.navigate("favoritos") }) {
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
            // Título de la sección "Tacos"
            Text(
                text = stringResource(R.string.tacos),
                style = MaterialTheme.typography.labelLarge
            )
            // Lista de lugares dentro de la categoría "Tacos"
            LazyColumn {
                items(Datasource().tacos()) { tacos ->
                    LugaresCard(
                        navController = navController,
                        lugar = tacos,
                        isFavorite = favoritosViewModel.isFavorite(tacos),
                        onFavoriteClick = { favoritosViewModel.agregarFavoritos(it) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

/**
 * Composable que muestra una tarjeta de información de un lugar.
 *
 * @param navController El NavController para manejar la navegación entre pantallas.
 * @param lugar El objeto Lugar que contiene la información del lugar.
 * @param isFavorite Booleano que indica si el lugar es favorito.
 * @param onFavoriteClick Callback que se ejecuta cuando se hace clic en el icono de favorito.
 * @param modifier Modificador para aplicar al Composable.
 */
@Composable
fun LugaresCard(
    navController: NavController,
    lugar: Lugar,
    isFavorite: Boolean,
    onFavoriteClick: (Lugar) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = LocalContext.current.getString(lugar.titulo),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                IconButton(onClick = { onFavoriteClick(lugar) }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorito",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(5.dp)
                            .size(30.dp)
                    )
                }
            }
            Image(
                painter = painterResource(lugar.imageResourceId),
                contentDescription = "tacos",
                modifier = Modifier
                    .width(350.dp)
                    .height(194.dp)
                    .padding(start = 60.dp, end = 30.dp),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "lugar",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .size(20.dp)
                )
                Text(
                    text = LocalContext.current.getString(lugar.ubicacion),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "descripcion",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .size(20.dp)
                )
                Text(
                    text = LocalContext.current.getString(lugar.descripcion),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "horario",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .size(20.dp)
                )
                Text(
                    text = LocalContext.current.getString(lugar.horario),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "celular",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .size(20.dp)
                )
                Text(
                    text = LocalContext.current.getString(lugar.telefono),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/**
 * Función de vista previa para la pantalla de "Tacos".
 */
@Preview(showBackground = true)
@Composable
fun TacosPreview() {
    GastronomíaGPSTheme {
        Tacos(navController = NavController(LocalContext.current), favoritosViewModel = FavoritosViewModel())
    }
}
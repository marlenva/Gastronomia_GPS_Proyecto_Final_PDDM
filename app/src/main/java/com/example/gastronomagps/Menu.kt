package com.example.gastronomagps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gastronomagps.data.Datasource
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController) {
    // Configuración del Scaffold que incluye una TopAppBar y una BottomAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.icono), // Imagen en la barra superior
                        contentDescription = null,
                        modifier = Modifier.size(85.dp).padding(15.dp)
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.height(65.dp),
                containerColor = Color(0xFF000080) // Color azul marino para el fondo
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
        // Contenido principal de la pantalla dentro de una LazyColumn
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Botones del menú para cada categoría
            item {
                MenuButton(navController, "tacos", R.drawable.tacos, R.string.tacos)
            }
            item {
                MenuButton(navController, "gorditas", R.drawable.gorditas, R.string.gorditas)
            }
            item {
                MenuButton(navController, "mariscos", R.drawable.mariscos, R.string.mariscos)
            }
            item {
                MenuButton(navController, "otros", R.drawable.otros, R.string.otros)
            }
        }
    }
}

/**
 * Composable que representa un botón de menú.
 *
 * @param navController El NavController para manejar la navegación entre pantallas.
 * @param route La ruta de navegación a la que se dirige el botón.
 * @param imageRes El recurso de la imagen que se muestra en el botón.
 * @param textRes El recurso del texto que se muestra en el botón.
 */
@Composable
fun MenuButton(
    navController: NavController,
    route: String,
    imageRes: Int,
    textRes: Int
) {
    Row(modifier = Modifier.padding(5.dp)) {
        Button(
            onClick = { navController.navigate(route) },
            modifier = Modifier
                .width(290.dp)
                .height(120.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = stringResource(textRes), style = MaterialTheme.typography.displayMedium)
            }
        }
    }
}

/**
 * Función de vista previa para la pantalla del menú.
 */
@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    GastronomíaGPSTheme {
        Menu(navController = rememberNavController())
    }
}
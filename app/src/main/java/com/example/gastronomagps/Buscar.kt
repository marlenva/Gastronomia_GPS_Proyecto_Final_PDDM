package com.example.gastronomagps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.gastronomagps.data.FavoritosViewModel
import com.example.gastronomagps.data.SearchViewModel
import com.example.gastronomagps.model.Lugar
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buscar(navController: NavController, viewModel: SearchViewModel) {
    val context = LocalContext.current
    val query by viewModel.query.collectAsState()
    val resultados by viewModel.resultados.collectAsState()
    Scaffold(
        topBar = {
            // Barra superior con título, ícono de navegación y acciones
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
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver al menú"
                        )
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.sin_fondo),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF000080), // Color azul marino
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            // Barra inferior con botones de búsqueda, inicio y favoritos
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.height(65.dp),
                containerColor = Color(0xFF000080)
            ) {
                IconButton(onClick = { navController.navigate("buscar") }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "buscar",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { navController.navigate("gpsApp") }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "principal",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "favoritos",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
                .padding(innerPadding)
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = query,
                onValueChange = { viewModel.onQueryChanged(it, context) },
                label = { Text("Buscar") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Lista de resultados de búsqueda
            LazyColumn {
                items(resultados) { lugar ->
                    LugarItem(lugar, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

/**
 * Función componible para mostrar un elemento de lugar individual.
 *
 * @param lugar El lugar para mostrar.
 * @param modifier El modificador para estilizar el elemento.
 */
@Composable
fun LugarItem(lugar: Lugar, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            // Título del lugar
            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = LocalContext.current.getString(lugar.titulo),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                // Icono de favorito
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "favorito",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(5.dp)
                        .size(30.dp)
                )
            }
            // Imagen del lugar
            Image(
                painter = painterResource(lugar.imageResourceId),
                contentDescription = "tacos",
                modifier = Modifier
                    .width(350.dp)
                    .height(194.dp)
                    .padding(start = 60.dp, end = 30.dp),
                contentScale = ContentScale.Crop
            )
            // Detalles del lugar: dirección, descripción, horario, teléfono
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
 * Función de vista previa para la pantalla de búsqueda.
 */
@Preview(showBackground = true)
@Composable
fun BuscarPreview() {
    GastronomíaGPSTheme {
        Buscar(navController = rememberNavController(), viewModel = SearchViewModel())
    }
}
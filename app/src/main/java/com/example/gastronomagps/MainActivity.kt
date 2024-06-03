package com.example.gastronomagps

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.gastronomagps.data.FavoritosViewModel
import com.example.gastronomagps.model.Lugar
import com.example.gastronomagps.ui.theme.GastronomíaGPSTheme
import java.util.Locale

/**
 * Actividad principal de la aplicación que maneja la navegación entre pantallas y la configuración de la interfaz de usuario.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Recordando el controlador de navegación
            val navController = rememberNavController()
            // Obteniendo una instancia del ViewModel de favoritos
            val favoritosViewModel: FavoritosViewModel = viewModel()
            // Aplicando el tema de la aplicación
            GastronomíaGPSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Configurando el NavHost para la navegación
                    NavHost(navController = navController, startDestination = "gpsApp") {
                        // Definiendo las composables para cada pantalla de la aplicación
                        composable("gpsApp") {
                            GpsApp(navController = navController, mainActivity = this@MainActivity)
                        }
                        composable("menu") {
                            Menu(navController = navController)
                        }
                        composable("tacos") {
                            Tacos(navController = navController, favoritosViewModel)
                        }
                        composable("gorditas") {
                            com.example.gastronomagps.Gorditas(
                                navController = navController,
                                favoritosViewModel
                            )
                        }
                        composable("mariscos") {
                            com.example.gastronomagps.Mariscos(
                                navController = navController,
                                favoritosViewModel
                            )
                        }
                        composable("otros") {
                            com.example.gastronomagps.Otros(
                                navController = navController,
                                favoritosViewModel
                            )
                        }
                        composable("buscar") {
                            Buscar(navController = navController, viewModel = viewModel())
                        }
                        composable("favoritos") {
                            Favoritos(navController = navController, favoritosViewModel)
                        }
                    }
                }
            }
        }
    }

    /**
     * Configura el idioma de la aplicación.
     *
     * @param languageCode El código del idioma (e.g., "es" para español, "en" para inglés).
     */
    fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate() // Recrea la actividad para aplicar el cambio de idioma
    }
}

/**
 * Función componible que muestra la pantalla principal de la aplicación con opciones de navegación.
 *
 * @param navController El NavController para manejar la navegación entre pantallas.
 * @param mainActivity La instancia de MainActivity para cambiar el idioma.
 * @param modifier El modificador para aplicar a la composición.
 */
@Composable
fun GpsApp(navController: NavController, mainActivity: MainActivity, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo con opacidad
        Image(
            painter = painterResource(R.drawable.colores),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )

        // Contenido principal de la pantalla dentro de un LazyColumn
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Elemento de texto con el nombre de la aplicación
            item {
                Text(
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000080),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(
                        top = 55.dp,
                        bottom = 20.dp,
                        start = 15.dp,
                        end = 15.dp
                    )
                )
            }
            // Imagen de portada
            item {
                Image(
                    painter = painterResource(R.drawable.portada),
                    contentDescription = "Apozol",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Descripción de la aplicación
            item {
                Text(
                    text = stringResource(R.string.descripcion),
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp)
                )
            }

            // Botón para comenzar y navegar al menú
            item {
                Button(onClick = {
                    navController.navigate("menu")
                }) {
                    Text(text = stringResource(R.string.comenzar), style = MaterialTheme.typography.displayMedium)
                }
            }

            // Botones para cambiar el idioma
            item {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Button(onClick = { mainActivity.setLocale("es") }) {
                        Text("ES", style = MaterialTheme.typography.displayMedium)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { mainActivity.setLocale("en") }) {
                        Text("EN", style = MaterialTheme.typography.displayMedium)
                    }
                }
            }
        }
    }
}

/**
 * Función de vista previa para la pantalla principal de la aplicación.
 */
@Preview(showBackground = true)
@Composable
fun GpsPreview() {
    val activity = LocalContext.current as MainActivity
    GastronomíaGPSTheme {
        GpsApp(navController = rememberNavController(), mainActivity = activity)
    }
}



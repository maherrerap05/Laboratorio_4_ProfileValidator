package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Datos simulados (Variables de nivel superior para acceso global)
val avatarUrl: String? = null
val userBio: String? = "Desarrollador Android en Formación."
val followerCount: String = "-1500"

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}

@Composable
fun UserProfileScreen() {

    // 1. Manejo seguro de nulidad con el Operador Elvis (?:)
    val bioDisplay = userBio ?: "Biografía no Disponible."

    // 1.5. EXTRA: Manejar Valores de Seguidores negativos o letras

    // Manejo de Letras en vez de números y manejo de valores negativos

    val followerCountNumero: Int = followerCount.toIntOrNull()?.takeIf { it >= 0} ?: 0


    // 2. 'when' como expresión para lógica de colores
    val statusColor = when {
        followerCountNumero == null -> Color.Gray
        followerCountNumero > 1000 -> Color(0xFFFFD700) // Dorado
        followerCountNumero > 100 -> Color.Blue
        else -> Color.DarkGray
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono genérico con color dinámico basado en nulidad
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = if(avatarUrl == null) Color.LightGray else Color.Green,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = bioDisplay, style = MaterialTheme.typography.bodyLarge)

        // Llamada segura (?.) para mostrar el número de seguidores
        Text (
            text = "Seguidores: ${followerCountNumero ?: 0}",
            color = statusColor,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}
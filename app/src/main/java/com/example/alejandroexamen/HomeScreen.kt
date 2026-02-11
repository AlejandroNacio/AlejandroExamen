package com.example.alejandroexamen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.alejandroexamen.MVVM.AñadirJugadoresViewModel
import com.example.alejandroexamen.MVVM.Jugador


@Composable
fun HomeScreen(
    viewModel: AñadirJugadoresViewModel = viewModel(),
    onNavigateToAdd: () -> Unit,
    onEditJugador: (Jugador) -> Unit
) {
    val jugadores by viewModel.jugadores.collectAsState()

    Scaffold(
        floatingActionButton = {
            Button(onClick = onNavigateToAdd) { Text("+") }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(jugadores) { jug ->
                JugadoresItemsCards(
                    jugador = jug,
                    onDelete = { viewModel.deleteJugador(jug.idJugador) },
                    onEdit = { onEditJugador(jug) }
                )
            }
        }
    }
}

@Composable
fun JugadoresItemsCards(jugador: Jugador, onDelete: () -> Unit, onEdit: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = onEdit
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = jugador.imagen,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(text = jugador.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "Nº ${jugador.numero} - ${jugador.nacionalidad}", style = MaterialTheme.typography.bodySmall)
                Text(text = jugador.posicion, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar jugador", tint = Color.Red)
            }
        }
    }
}
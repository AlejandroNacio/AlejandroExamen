package com.example.alejandroexamen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.alejandroexamen.MVVM.AñadirJugadoresViewModel
import com.example.alejandroexamen.MVVM.Jugador

//@Composable
//fun HomeScreen(viewModel: AñadirJugadoresViewModel = viewModel()){
//    LazyColumn {
//        items(jugadores) { jug ->
//            JugadoresItemsCards(
//                jugadores = jug,
//                onDelete = { viewModel.deleteJugador(jug.idJugador) }
//            )
//        }
//    }
//}

@Composable
fun JugadoresItemsCards( jugador: Jugador,
                        onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            AsyncImage(
//                model = Jugador.imagenURL,
//                contentDescription = "Imagen de ${jugador.nombre}",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(150.dp),
//                contentScale = ContentScale.Crop
//            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = jugador.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "${jugador.nacionalidad} / ${jugador.nacionalidad} ", style = MaterialTheme.typography.bodySmall)
                Text(text = "${jugador.posicion}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    }
}
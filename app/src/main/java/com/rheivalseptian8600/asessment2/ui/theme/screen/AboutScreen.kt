package com.rheivalseptian8600.asessment2.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Nama : Rheival Septian", style = MaterialTheme.typography.bodyLarge)
            Text("Kelas : D3IF-48-01", style = MaterialTheme.typography.bodyLarge)
            Text("NIM : 607062400086", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Aplikasi MoneyPath", style = MaterialTheme.typography.titleMedium)
            Text(
                "Aplikasi ini digunakan untuk mencatat pengeluaran harian. " +
                        "Pengguna dapat menambah, mengubah, dan menghapus catatan pengeluaran " +
                        "yang tersimpan secara lokal di perangkat.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
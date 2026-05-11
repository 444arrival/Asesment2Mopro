package com.rheivalseptian8600.asessment2.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rheivalseptian8600.asessment2.ui.theme.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    id: Int = -1,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val data by viewModel.data.collectAsState()
    val existing = data.find { it.id == id }

    var nama by remember { mutableStateOf("") }
    var nominal by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(existing) {
        existing?.let {
            nama = it.nama
            nominal = it.nominal.toString()
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("Hapus pengeluaran ini?") },
            confirmButton = {
                TextButton(onClick = {
                    existing?.let { viewModel.hapusData(it) }
                    showDialog = false
                    navController.popBackStack()
                }) { Text("Hapus") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (id == -1) "Tambah Pengeluaran" else "Ubah Pengeluaran") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    if (id != -1) {
                        Box {
                            IconButton(onClick = { showMenu = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                            }
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Hapus") },
                                    onClick = {
                                        showMenu = false
                                        showDialog = true
                                    }
                                )
                            }
                        }
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text("Nama Pengeluaran") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nominal,
                onValueChange = { nominal = it },
                label = { Text("Nominal (Rp)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = {
                    when {
                        nama.isEmpty() -> {
                            Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                        }
                        nominal.isEmpty() -> {
                            Toast.makeText(context, "Nominal tidak boleh kosong", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            if (id == -1) {
                                viewModel.simpanData(nama, nominal)
                            } else {
                                viewModel.updateData(id, nama, nominal)
                            }
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}
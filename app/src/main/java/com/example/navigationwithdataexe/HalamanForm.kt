package com.example.navigationwithdataexe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanForm(
    onConfirmButtonClicked: (MutableList<String>) -> Unit,
    dosenPembimbing: List<String>,
    dosenPembimbing2: List<String>,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (String) -> Unit,
    onSelectionChanged2: (String) -> Unit,
) {
    var namamhs by rememberSaveable { mutableStateOf("") }
    var nim by rememberSaveable { mutableStateOf("") }
    var konsentrasi by rememberSaveable { mutableStateOf("") }
    var judul by rememberSaveable { mutableStateOf("") }
    var dosenYgDipilih by remember { mutableStateOf("") }
    var dosenYgDipilih2 by remember { mutableStateOf("") }
    var listData: MutableList<String> = mutableListOf(namamhs, nim, konsentrasi, judul)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.95f)
                    .padding(vertical = 50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                )
                {
                    Text(text = "Formulir Mahasiswa")
                    OutlinedTextField(
                        value = namamhs,
                        onValueChange = { namamhs = it },
                        label = { Text(text = "Nama Mahasiswa") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                    )
                    OutlinedTextField(
                        value = nim,
                        onValueChange = { nim = it },
                        label = { Text(text = "NIM") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                    )
                    OutlinedTextField(
                        value = konsentrasi,
                        onValueChange = { konsentrasi = it },
                        label = { Text(text = "Konsentrasi") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                    )
                    OutlinedTextField(
                        value = judul,
                        onValueChange = { judul = it },
                        label = { Text(text = "Judul Skripsi") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(stringResource(id = R.string.dospem_1))
                        Text(stringResource(id = R.string.dospem_2))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            dosenPembimbing.forEach { item ->
                                Row(
                                    modifier = Modifier.selectable(
                                        selected = dosenYgDipilih == item,
                                        onClick = {
                                            dosenYgDipilih = item
                                            onSelectionChanged(item)
                                        }
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(selected = dosenYgDipilih == item,
                                        onClick = {
                                            dosenYgDipilih = item
                                            onSelectionChanged(item)
                                        }
                                    )
                                    Text(item)
                                }
                            }
                        }
                        Column {
                            dosenPembimbing2.forEach { item ->
                                Row(
                                    modifier = Modifier.selectable(
                                        selected = dosenYgDipilih2 == item,
                                        onClick = {
                                            dosenYgDipilih2 = item
                                            onSelectionChanged2(item)
                                        }
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(selected = dosenYgDipilih2 == item, onClick = {
                                        dosenYgDipilih2 = item
                                        onSelectionChanged2(item)
                                    }
                                    )
                                    Text(item)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = { onConfirmButtonClicked(listData) },
                        enabled = namamhs.isNotEmpty() && nim.isNotEmpty() && konsentrasi.isNotEmpty() && judul.isNotEmpty() && dosenYgDipilih.isNotEmpty(),
                    )
                    {
                        Text(text = "Next")
                    }
                    Button(
                        onClick = onCancelButtonClicked
                    ) {
                        Text(stringResource(R.string.back_button))
                    }
                    }
                }
            }
        }
    }

package com.example.navigationwithdataexe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DosenDetailScreen(
    onConfirmButtonClicked: (String, String, String, String) -> Unit,
    onCancelButtonClicked: () -> Unit,
    dosenPembimbing: List<String>,
    onSelectionChanged: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    var namamhs by rememberSaveable { mutableStateOf("") }
    var nim by rememberSaveable { mutableStateOf("") }
    var konsentrasi by rememberSaveable { mutableStateOf("") }
    var judul by rememberSaveable { mutableStateOf("") }
    var dosenYgDipilih by remember {mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
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
        Column (
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ){
            dosenPembimbing.forEach { item ->
                Row (
                    modifier = Modifier.selectable(
                        selected = dosenYgDipilih == item,
                        onClick = {
                            dosenYgDipilih = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(selected = dosenYgDipilih == item, onClick = {
                        dosenYgDipilih = item
                        onSelectionChanged(item)
                    })
                    Text(item)
                    RadioButton(selected = dosenYgDipilih == item, onClick = {
                        dosenYgDipilih = item
                        onSelectionChanged(item)
                    })
                    Text(item)
                }
            }
            Button(
                onClick = {
                    if (namamhs.isNotEmpty() && nim.isNotEmpty() && konsentrasi.isNotEmpty() && judul.isNotEmpty()) {
                        onConfirmButtonClicked(namamhs, nim, konsentrasi, judul)
                    }

                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

package com.example.navigationwithdataexe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navigationwithdataexe.data.UIState

@Composable
fun HalamanSummary(
    uiState: UIState,
    onCancelButtonClicked: () -> Unit,
    ){
    val items = listOf(
        Pair("Nama Mahasiswa", uiState.namamhs),
        Pair("NIM", uiState.nim.toString()),
        Pair("Konsentrasi", uiState.konsentrasi),
        Pair("Judul", uiState.judul),
        Pair("Dosen Pembimbing1", uiState.dosen1),
        Pair("Dosen Pembimbing2", uiState.dosen2),
    )
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            Column(
                modifier = Modifier
                    .padding(10.dp),
            ) {
                items.forEach { item ->
                    Column {
                        Text(item.first.uppercase())
                        Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                    }
                    Divider(thickness = 1.dp)
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
            Row(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .weight(1f, false)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                ) {
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCancelButtonClicked
                    ) {
                        Text(stringResource(R.string.back_button))
                    }
                }
            }
        }
    }
}
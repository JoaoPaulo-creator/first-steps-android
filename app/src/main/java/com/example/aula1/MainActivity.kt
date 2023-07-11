package com.example.aula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1.ui.theme.Aula1Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun add(transaction: String) {
        val transactions = _uiState.value.transactions.toMutableList()
        transactions.add(transaction)
        _uiState.value = UiState(transactions = transactions)
    }

    data class UiState(
        val transactions: List<String> = emptyList()
    ) {}
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula1Theme {
                Column {
                    Welcome()
                    Transactions()
                }
            }
        }
    }
}

@Composable
fun Transactions(viewModel: MyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        /*
        LazyColumn cria uma barra de rolagem. O que torna esse processo performatico,
        eh que o app vai renderizar somente o que esta sendo visivel ao usuario
        *
        * */
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Text(text = "Header")
            }
            
            items(uiState.transactions.size) { index ->
                Transaction(uiState.transactions[index])
            }

            item {
                Text(text = "Footer")
            }

        }
        Button(onClick = {
            viewModel.add("Nova transacao")
        }) {
            Text(text = "Add new transaction")
        }
    }
}

@Composable
private fun Transaction(transaction: String) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = transaction,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun Welcome() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Welcome back \n Joao",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f) // isso aqui move o icone para o canto direito
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Clear transactions",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    Aula1Theme {
        Column {
            Welcome()
            Transactions()
        }
    }
}

/*
*
*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Aula1Theme {
        Greeting("Joao Pauludo")
    }
}
*
* @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // criando um state

    /*
    O remember garante que o valor anterior seja "lembrado", ou seja, sempre que botao eh clicado
    o componente eh composto novamente, porem, o estado dele inicia em 0, sendo assim, o front sempre
    vai renderizar o numero 0. Com o remeber, o prevState sera incrementado, e entao o valor esperado
    sera renderizado.

    Ja o remeberSaveable, eh utilizado quando a activity eh recriada, salvando/relembrando do estado
    anterior
    * */
    val times = rememberSaveable {
        mutableStateOf(0)
    }

    /**
     *  Aqui, o papel do modifier é ser a propriedade que vai receber os valores referentes a estilização dos componentes
     *  Neste cenario, o modifier do column está setando um padding nos textos e botoes do container
     */

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Spacer(modifier = modifier.padding(16.dp))
        Text(
            text = "Clicked ${times.value}",
            modifier = modifier,
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = {
                // pegando o valor do estado e incrementado-o
                times.value++
            },
            /**
             *  Ja aqui, estamos setando para que o componente ocupe a largura inteira
             */
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Click here")
        }
    }

}
*
*
*
* */
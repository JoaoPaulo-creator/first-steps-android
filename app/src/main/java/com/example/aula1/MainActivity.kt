package com.example.aula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aula1.ui.theme.Aula1Theme

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
fun Transactions() {

    /*
    Essa funcao cria uma barra de rolagem. O que torna esse processo performatico,
    eh que o app vai renderizar somente o que esta sendo visivel ao usuario
    *
    * */

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(transactionsDummy.size) { index ->
            Transaction(index)
        }
    }
}

@Composable
private fun Transaction(index: Int) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = transactionsDummy[index],
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


private val transactionsDummy = listOf(
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
    "Gasolina", "Internet", "Luz", "Academia", "lanchos",
)


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
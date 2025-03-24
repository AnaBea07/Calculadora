package com.atividade.atividadecalculadora


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CalculatorView() {

    var displayCalculadora by remember{ mutableStateOf("0") }
    var num1 by remember { mutableIntStateOf(0) }
    var num2 by remember { mutableIntStateOf(0) }
    var operador by remember { mutableStateOf("") }
    var result by remember { mutableIntStateOf(0) }

    fun onCClick() {
        displayCalculadora = "0"
        num1 = 0
        num2 = 0
        operador = ""
        result = 0
    }

    fun onNumberClick(number: String) {
        if (displayCalculadora == "0") {
            displayCalculadora = number
        } else {
            displayCalculadora += number
        }

        num2 = displayCalculadora.toInt()
    }

    fun calculateResult() {
        if (operador == "+" || operador == "-" || operador == "*" || operador == "/") {
            if (operador == "+") {
                result = num1 + num2
            } else if (operador == "-") {
                result = num1 - num2
            } else if (operador == "*") {
                result = num1 * num2
            } else if (operador == "/") {
                result = if (num2 != 0) num1 / num2 else 0 // Evitando divisão por zero
            }

            displayCalculadora = result.toString()
            num1 = result
            num2 = 0
        }
    }

    fun onEqualClick() {
        if (num1 != null && num2 != null && operador != null) {
            calculateResult()
        }
    }

    fun onOperatorClick(operator: String) {

        if (num1 != null && num2 != null && operador != null) {
            calculateResult()
        }
        operador = operator
        num1 = displayCalculadora.toInt()
        displayCalculadora = "0"
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = displayCalculadora,
            modifier = Modifier
                .padding(start = 200.dp),
            textAlign = TextAlign.End,
            fontSize = 36.sp

        )
        Row {
            Button(onClick = { onNumberClick("1")}) { Text(text = "1") }
            Button(onClick = { onNumberClick("2")}) { Text(text = "2") }
            Button(onClick = { onNumberClick("3")}) { Text(text = "3") }
            Button(onClick = {onOperatorClick("+")}) { Text(text = "+") }
        }
        Row {
            Button(onClick = {onNumberClick("4")}) { Text(text = "4") }
            Button(onClick = {onNumberClick("5")}) { Text(text = "5") }
            Button(onClick = { onNumberClick("6")}) { Text(text = "6") }
            Button(onClick = {onOperatorClick("-")}) { Text(text = "-") }
        }
        Row {
            Button(onClick = {onNumberClick("7")}) { Text(text = "7") }
            Button(onClick = {onNumberClick("8")}) { Text(text = "8") }
            Button(onClick = {onNumberClick("9")}) { Text(text = "9") }
            Button(onClick = {onOperatorClick("*")}) { Text(text = "*") }
        }
        Row {
            Button(onClick = {onCClick()}) { Text(text = "C") }
            Button(onClick = {onNumberClick("0")}) { Text(text = "0") }
            Button(onClick = {onEqualClick()}) { Text(text = "=") }
            Button(onClick = {onOperatorClick("/")}) { Text(text = "/") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCalculadora() {
    CalculatorView()
}

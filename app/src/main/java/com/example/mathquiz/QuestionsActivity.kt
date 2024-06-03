package com.example.mathquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mathquiz.ui.theme.Purple40

@Composable
fun QuestionsScreen(navController: NavHostController) {
    var answers by remember { mutableStateOf(List(5) { "" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(color = Color.White), // Set background color
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .background(color = Purple40)
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        ) {
            Text(
                text = "Math Quiz",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White // Set text color

            )

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.Blue)
        )

        Spacer(modifier = Modifier.height(16.dp))

        for (index in 0 until 5) {
            if (index == 4) {
                TrueFalseInput(
                    question = getQuestion(index),
                    onAnswerChange = { answer ->
                        answers = answers.toMutableList().also {
                            it[index] = answer
                        }
                    }
                )
            } else {
                QuestionInput(
                    question = getQuestion(index),
                    answer = answers[index],
                    onAnswerChange = { answer ->
                        answers = answers.toMutableList().also {
                            it[index] = answer
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp)) // Fill the remaining space
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    answers = List(5) { "" }
                },
                modifier = Modifier.padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                )

            ) {
                Text(text = "Reset", color = Purple40)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    navController.navigate("result/${answers.joinToString(",")}") {
                        launchSingleTop = true
                    }
                }
            ) {
                Text(text = "Show Result")
            }
        }
//        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionInput(question: String, answer: String, onAnswerChange: (String) -> Unit) {
    var userAnswer by remember { mutableStateOf(answer) }

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black // Set text color
        )
        OutlinedTextField(
            textStyle = TextStyle(fontSize = 18.sp),
            value = userAnswer,
            onValueChange = {
                userAnswer = it
                onAnswerChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Answer") }
        )
    }
    LaunchedEffect(answer) {
        userAnswer = answer
    }
}

@Composable
fun TrueFalseInput(question: String, onAnswerChange: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black // Set text color
        )
        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !selectedOption,
                onClick = {
                    selectedOption = false
                    onAnswerChange("true")
                }
            )
            Text(text = "True", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedOption,
                onClick = {
                    selectedOption = true
                    onAnswerChange("false")
                }
            )
            Text(text = "False", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

fun getQuestion(index: Int): String {
    return when (index) {
        0 -> "What is the result of 20 + 77?"
        1 -> "What is the result of 100 - 42?"
        2 -> "What is the result of 9 * 6?"
        3 -> "What is the result of 81 / 9?"
        4 -> "17 + 25 = 40, is the equation correct?"
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    val navController = rememberNavController()
    QuestionsScreen(navController = navController)
}

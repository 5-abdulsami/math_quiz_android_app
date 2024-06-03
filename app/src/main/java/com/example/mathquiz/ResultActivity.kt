package com.example.mathquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mathquiz.ui.theme.Purple40

@Composable
fun ResultScreen(navController: NavHostController, answers: List<String>) {
    val correctAnswers = listOf("97", "58", "54", "9", "false")
    val correctCount = answers.zip(correctAnswers).count { it.first == it.second }

    val score = (correctCount.toDouble() / 5 * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Box(
            modifier = Modifier
                .background(color = Purple40, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Your Score: $score%",
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Divider
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Correct Answers Section
        Column {
            Text(
                text = "Answers:",
                color = Color.Black,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            for (index in 0 until answers.size) {
                val answerText = when (index) {
                    4 -> {
                        val isCorrect = answers[index] == correctAnswers[index]
                        val answer = if (isCorrect) "False" else "True"
                        "Question ${index + 1}: $answer"
                    }
                    else -> "Question ${index + 1}: ${answers[index]}"
                }
                val color = if (answers[index] == correctAnswers[index]) Color.Green else Color.Red
                Text(
                    text = answerText,
                    color = color,
                    fontSize = 24.sp
                )
            }
        }

        // Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val navController = rememberNavController()
    val answers = listOf("100", "58", "54", "9", "true")
    ResultScreen(navController = navController, answers = answers)
}

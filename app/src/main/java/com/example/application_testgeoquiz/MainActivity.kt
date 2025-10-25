package com.example.application_testgeoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application_testgeoquiz.ui.theme.ApplicationtestGeoQuizTheme
import kotlin.time.Duration.Companion.seconds

class QuziViewModel : ViewModel() {
    var score by mutableStateOf(0)
        private set

    var currentQuestionIndex by mutableStateOf(0)
        private set

    var userAnswered by mutableStateOf(False)
        private set

    private val questions = listOf(
        "Canberra is the capital of Australia." to true,
        "The Pacific Ocean is larger than the Atlantic Ocean?" to true,
        "The Suez Canal connects the Red Sea and the Indian Ocean." to false,
        "The source of the Nile River is in Egypt." to false,
        "The Amazon River is the longest river in the Americas." to true,
        "Lake Baikal is the world's oldest and deepest freshwater lake." to true
    )
    val currentQuestion : String
        get() = questions[currentQuestionIndex].first

    val currentAnswer : Boolean
        get() = questions[currentQuestionIndex].second

    val totalQuestions : Int
        get() = questions.size

    val isLastQuestion : Boolean
        get() = currentQuestionIndex == questions.size - 1

    fun answerQuestion(userAnswer: Boolean) {
        if(!userAnswered) {
            if(userAnswer == currentAnswer) {
                score++
            }
            userAnswered = true
        }
    }
    fun nextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            userAnswered = false
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationtestGeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopLevel(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    viewModel : QuziViewModel = viewModel()
) {
    Text(
        text = "Score : ${viewModel.score}",
        modifier = modifier
    )
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    viewModel: QuziViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = ViewModel.currentWuestion,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        if(!viewModel.userAnswered) {
            Row(
                horizontalArragment = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { "" }
                ) {
                    Text("True")
                }

                Button(
                    onClick = { "" }
                ) {
                    Text("False")
                }
            }

        if(!viewModel.userAnswered && !viewModel.isLastQuestion) {
            Button(
                onClick = { "" },
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text("Next")
            }
        }
    }
}
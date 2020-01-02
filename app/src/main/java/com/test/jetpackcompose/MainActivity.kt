package com.test.jetpackcompose

import android.os.Bundle
import android.widget.AbsListView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.memo
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import java.text.Normalizer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                GreetingTwo(name = "Alan!!")
            }
        }
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit){
    MaterialTheme {
        Surface(color = Color.Yellow) {
            children()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Yellow) {
        Text(text = "Hello $name!", modifier = Spacing(24.dp))
    }
}

@Composable
fun GreetingTwo(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Spacing(24.dp),
        style = (+MaterialTheme.typography()).h1)
}

@Preview("Text Preview")
@Composable
fun DefaultPreview() {
    MaterialTheme {
        GreetingTwo(name = "Alan!!")
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Alan", "Jorge", "Eduardo","Alan", "Jorge", "Eduardo","Alan", "Jorge", "Eduardo","Alan", "Jorge", "Eduardo"),
                    counterState: CounterState = CounterState()){
    VerticalScroller {
        Column(modifier = ExpandedHeight) {
            Column{
                for (name in names) {
                    Greeting(name = name)
                    Divider(color = Color.Black)
                }
                Divider(color = Color.Transparent, height = 32.dp)
                Counter(counterState)
            }
        }
    }
}

@Composable
fun Counter(state: CounterState){
    Button(
        text = "Haz presionado ${state.count} veces",
        onClick = {
            state.count++
        },
        style = ContainedButtonStyle(color = if (state.count > 6) Color.Green  else Color.White )
    )
}

@Composable
fun Form(formState: FormState){
    Checkbox(
        checked = formState.optionChecked,
        onCheckedChange = { newState -> formState.optionChecked = newState })
}

@Model
class CounterState(var count: Int = 0)

@Model
class FormState(var optionChecked: Boolean)


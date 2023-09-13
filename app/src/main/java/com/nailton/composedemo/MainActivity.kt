package com.nailton.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.nailton.composedemo.ui.theme.ComposeDemoTheme
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
private fun SizeText(value: String) {
    Text(
        text = value+"sp".uppercase(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight(600)
        ),
        modifier = Modifier.padding(30.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun DemoText(name: String, state: Float) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        color = Color.Blue,
        text = "Hello $name!, Welcome to Compose".uppercase(),
        style = TextStyle(
            fontSize = state.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight(600),
            shadow = Shadow(
                color = Color.Red, offset = offset, blurRadius = 3f
            )
        ),
        modifier = Modifier
            .padding(10.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun DemoSlider(initialSliderPosition: Float,
                       onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..50f,
        value = initialSliderPosition,
        colors = SliderDefaults.colors(
            thumbColor = Color.Blue,
            activeTrackColor = Color.Cyan,
            inactiveTrackColor = Color.Blue
        ),
        onValueChange = {
            onPositionChange(it)
        }
    )
}

@Composable
private fun DemoScreen() {
    // definindo estado mutavel
    var state by remember { mutableStateOf(20f) }

    // definindo funcao para mudanca do estado
    val handlePositionChange = { it: Float ->
        state = it
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        DemoText("Nailton", state)
        // definindo espaco entre components com o spacer
        Spacer(modifier = Modifier.height(60.dp))
        DemoSlider(state, handlePositionChange )
        Spacer(modifier = Modifier.height(60.dp))
        SizeText(state.toInt().toString())
    }
}


@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ComposeDemoTheme {
        DemoScreen()
    }
}
package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }

    }

    @Composable
    private fun App(){
        var shouldShowOnboarding by remember{ mutableStateOf(false) }

        if (shouldShowOnboarding)
            Greetings()
        else
            OnboardingScreen({ shouldShowOnboarding = shouldShowOnboarding.not() })
    }

    @Composable
    private fun Greetings(
        modifier: Modifier = Modifier,
        names: List<String> = List(1000){"$it"}
    ) {
        LazyColumn {
             items(items = names) { name->
                   Greeting(name = name)
             }
        }

    }

    @Composable
    fun Greeting(name: String) {

        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        var isExpanded by rememberSaveable {mutableStateOf(false)}
        val extraPadding by animateDpAsState(targetValue = if (isExpanded) 48.dp else 0.dp,
            animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        )
        )
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(Modifier.padding(24.dp).background( if (isExpanded)color else Color.Transparent)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                    Text(text = "Hello,")
                    Text(text = name)
                }

                Button(onClick = { isExpanded = isExpanded.not() },
                    shape =  RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
                ) {
                  Text(text = if(isExpanded) "show less" else "show more")
                }
            }

        }
    }

    /*@Preview(showBackground = true , widthDp = 320)
    @Composable
    private fun PreviewMyApp() {
        MyApp(Modifier.fillMaxSize())
    }*/

    @Composable
    fun OnboardingScreen(
        onContinueClicked:() -> Unit,
        modifier: Modifier = Modifier ) {
        // TODO: This state should be hoisted
        var shouldShowOnboarding by remember { mutableStateOf(true) }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick =  onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }

    @Preview(showBackground = true, widthDp = 320, heightDp = 320)
    @Composable
    fun OnboardingPreview() {
      App()
    }
}
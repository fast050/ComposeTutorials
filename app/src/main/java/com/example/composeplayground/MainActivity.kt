package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds.remember
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
             Conversation(messages = SampleData.conversationSample)
        }

    }

    @Composable
    fun Conversation(messages:List<Message>){
        LazyColumn{
            items(messages){message->
                messageCard(message = message)
            }
        }
    }

    @Composable
    fun messageCard(message:Message){
        Row(modifier = Modifier.padding(8.dp)) {

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }

           Image(painter = painterResource(id = message.image),
                 contentDescription = "default image",
                 modifier = Modifier
                     .size(40.dp)
                     .clip(CircleShape)
                     .border(1.5.dp, MaterialTheme.colors.primary, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Column( modifier = Modifier.clickable { isExpanded = !isExpanded }){
                Text(text = message.author)
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(text = message.body , maxLines = if (isExpanded) Int.MAX_VALUE else 1)
                }

            }
        }


    }

 /*   @Preview
    @Composable
    fun PreviewMessageCard(){
        MaterialTheme {
            Surface {
                messageCard(message = Message("khalid","hello guys" , R.drawable.ic_launcher_background))
            }
        }
    }

    @Preview
    @Composable
    fun PreviewDarkMessageCard(){
        ComposePlayGroundTheme(true) {
            Surface {
                messageCard(message = Message("khalid","hello guys" , R.drawable.ic_launcher_background))
            }
        }
    }*/

    @Preview
    @Composable
    fun PreviewConversation(){
        Conversation(messages = SampleData.conversationSample)
    }
}
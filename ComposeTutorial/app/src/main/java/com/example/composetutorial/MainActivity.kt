package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewConversation()
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp) // set image size to 40dp
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        MessageCard(
            msg = Message("Colleage", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}


@Preview(name = "Conversation Day Mode")
@Preview(
    name = "Conversation Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

data class Topic(val image: Painter, val name: String)

@Composable
fun TopicChip(topic: Topic, selected: Boolean) {
    val radius by animateDpAsState(
        if (selected) 20.dp else 0.dp
    )

    Card(
        shape = RoundedCornerShape(
            topStart = radius
        )
    ) {
        Row {
            Box {
                Image(
                    painter = topic.image,
                    contentDescription = "topic image",
                    modifier = Modifier.size(100.dp, 100.dp)
                )
                if (selected) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "done_icon",
                        modifier = Modifier.size(100.dp, 100.dp)
                    )
                }
            }
            Text(
                text = topic.name,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "TopicChip_selected")
@Composable
fun PreviewTopicChip() {
    ComposeTutorialTheme {
        TopicChip(
            topic = Topic(
                painterResource(id = R.drawable.ic_launcher_foreground),
                "Topic Name"
            ),
            true
        )
    }
}

@Preview(name = "TopicChip_notSelected")
@Composable
fun PreviewTopicChipNotSelected() {
    ComposeTutorialTheme {
        TopicChip(
            topic = Topic(
                painterResource(id = R.drawable.ic_launcher_foreground),
                "Topic Name"
            ),
            false
        )
    }
}
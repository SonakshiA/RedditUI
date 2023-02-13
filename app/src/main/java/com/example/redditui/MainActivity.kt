package com.example.redditui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFF222222))
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LeftTopBar(modifier = Modifier.fillMaxWidth(.3f))
                        RightTopBar(modifier = Modifier.fillMaxWidth(.3f))
                    }
                    PostSection(
                        postInfo = listOf(
                            ImageWithText(
                                image = painterResource(id = R.drawable.apple),
                                subreddit = "r/apple",
                                userName = "u/helloworld",
                                headline = "MagSafe Apple Case animation only works on the phone it was designed for… How?",
                                supportingText = "If you buy say the genuine apple leather MagSafe case for the iPhone 13 Pro Max and put in the phone, " +
                                        "it does an animation, but if you put a newer or older iPhone on the same version, say the 14 Pro into the case, " +
                                        "it doesn’t do the animation… why and how?"
                            ),
                            ImageWithText(
                                image = painterResource(id = R.drawable.devindia),
                                subreddit = "r/developersIndia",
                                userName = "u/onetwothreefour",
                                headline = "What's a good place to live in India?",
                                supportingText = "Permanent WFH, want to shift to a nice city where I can meet like minded people. " +
                                        "Bangalore is of course an option but the whole point of WFH is to avoid the traffic and rent. " +
                                        "What other cities fit the bill?"
                            ),
                            ImageWithText(
                                image = painterResource(id = R.drawable.natgeo),
                                subreddit = "r/nationalgeographic",
                                userName = "u/hansvdlinden",
                                headline = "Question on a subscription and special publications",
                                supportingText = "Im more interested in the special publications and was wondering if they are included in the print and digital subscription package. " +
                                        "If not, is there a way to subscribe to them?"
                            ),
                            ImageWithText(
                                image = painterResource(id = R.drawable.technology),
                                subreddit = "r/technology",
                                userName = "u/hurdee",
                                headline = "Google search chief warns AI chatbots can give 'convincing but completely fictitious' answers, report says",
                                supportingText = ""
                            )
                        )
                    )
                }
                BottomMenu(
                    items = listOf(
                        BottomMenuItems("Home", R.drawable.home),
                        BottomMenuItems("Discover", R.drawable.compass),
                        BottomMenuItems("Create", R.drawable.add),
                        BottomMenuItems("Chat", R.drawable.chat_bubble),
                        BottomMenuItems("Inbox", R.drawable.bell)
                    ),
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = "Display Picture",
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RightTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.padding(top = 20.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(32.dp),
        )

        RoundImage(
            image = painterResource(id = R.drawable.avatar),
            modifier = Modifier
                .size(36.dp)
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun LeftTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.padding(start = 10.dp, top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_menu_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(36.dp)
        )

        val options = listOf("Home", "Popular", "News", "Latest")
        var selectedOption by remember {
            mutableStateOf("")
        }
        Spinner(
            itemList = options,
            selectedItem = selectedOption,
            onItemSelected = { selectedOption = it },
            modifier = Modifier
                .padding(start = 10.dp)

        )
    }
}


@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    selectedItem: String,
    onItemSelected: (selectedItem: String) -> Unit,
) {
    var tempSelectedItem = selectedItem

    if (tempSelectedItem.isEmpty() && itemList.isNotEmpty()) {
        onItemSelected(itemList[0])
        tempSelectedItem = itemList[0]
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    OutlinedButton(
        onClick = {
            expanded = true
        },
        modifier = modifier.clip(RoundedCornerShape(7.dp)),
        enabled = itemList.isNotEmpty(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF545452))
    ) {

        Text(
            text = tempSelectedItem,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(22.dp)

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF222222)),
            offset = DpOffset(0.dp, 18.dp)
        ) {
            itemList.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onItemSelected(it)
                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(5.dp)
                            )
                            .background(
                                if (tempSelectedItem == it) Color.DarkGray else Color(
                                    0xFF222222
                                )
                            )
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var id = 0
                        when (it) {
                            "Home" -> id = R.drawable.home
                            "Popular" -> id = R.drawable.arrow
                            "News" -> id = R.drawable.newspaper
                            else -> id = R.drawable.sparkling
                        }
                        Icon(
                            painter = painterResource(id = id),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 15.dp),
                            tint = Color.White
                        )
                        Text(
                            text = it,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    postInfo: List<ImageWithText>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(postInfo.size) {
                PostItem(item = postInfo[it])
            }
        }
    }
}

@Composable
fun PostItem(item: ImageWithText) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color(0xFF222222))
    ) {
        Column() {
            Top(
                image = item.image,
                subreddit = item.subreddit,
                userName = item.userName
            )
            Text(
                text = item.headline,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )

            Text(
                text = item.supportingText,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 10.dp,
                    bottom = 20.dp,
                    end = 20.dp
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            InteractiveIcons(modifier = Modifier.background(Color(0xFF222222)))
        }
    }
}

@Composable
fun SubInfo(
    subreddit: String,
    userName: String
) {
    Column(modifier = Modifier.padding(start = 10.dp)) {
        Text(
            text = subreddit,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Text(
            text = userName,
            color = Color.LightGray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun Top(
    image: Painter,
    subreddit: String,
    userName: String
) {
    Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp)) {
        RoundImage(image = image, modifier = Modifier.size(38.dp))
        SubInfo(subreddit = subreddit, userName = userName)
    }
}


@Composable
fun InteractiveIcons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp)
    ) {
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.up_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
            )
            Text(
                text = "Vote",
                color = Color.White,
                fontSize = 14.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.up_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(30.dp))

        Row() {
            Icon(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(20.dp)
            )

            Text(
                text = "7",
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(30.dp))

        Row() {
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
            )
            Text(
                text = "Share",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuItems>,
    modifier: Modifier = Modifier,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Gray,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF222222))
            .padding(8.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuItems,
    isSelected: Boolean = false,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Gray,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId), contentDescription = null,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor,
            fontSize = 12.sp
        )
    }
}

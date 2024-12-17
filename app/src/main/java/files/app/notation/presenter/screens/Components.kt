package files.app.notation.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import files.app.notation.presenter.screens.navigation.Screens
import files.app.notation.ui.theme.customBlackTwo
import files.app.notation.ui.theme.customBrown
import files.app.notation.ui.theme.customTextColor

@Composable
fun ShortNoteBox(navController: NavController, note: Note) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(customBlackTwo)
                .clickable {
                    navController.navigate(Screens.Note.route)
                },
            colors = CardDefaults.cardColors(
                containerColor = customBlackTwo
            )
        ) {
            Text(
                text = note.content,
                color = customTextColor,
                modifier = Modifier.padding(10.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = note.title,
            fontWeight = FontWeight.Bold,
            color = customTextColor,
            fontSize = 18.sp,
            modifier = Modifier.width(160.dp),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun CustomDropDownMenu(show: MutableState<Boolean>, content: @Composable () -> Unit) {
    DropdownMenu(
        expanded = show.value,
        onDismissRequest = { show.value = false },
        Modifier
            .background(customBlackTwo)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, customBrown, RoundedCornerShape(10.dp))
    ) {
        content.invoke()
    }
}

@Composable
fun CustomDropdownMenuItem(title: String, toDo: () -> Unit) {
    DropdownMenuItem(
        text = { Text(text = title, color = customTextColor) }, onClick = { toDo.invoke() },
        Modifier.background(customBlackTwo),
    )
}
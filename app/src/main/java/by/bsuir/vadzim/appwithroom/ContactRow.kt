package by.bsuir.vadzim.appwithroom

import android.media.metrics.Event
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactRow(
    contact: Contact,
    onEvent: (ContactEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape((12.dp)))
            .border(0.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {  }
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = contact.phoneNumber,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
        IconButton(
            onClick = {
                onEvent(ContactEvent.DeleteContact(contact))
            },
            modifier = Modifier.size(48.dp).offset(x =  (-10).dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Contact",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
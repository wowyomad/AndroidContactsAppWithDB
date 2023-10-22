package by.bsuir.vadzim.appwithroom

import android.media.metrics.Event
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                fontSize = 20.sp
            )
            Text(text = contact.phoneNumber, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onEvent(ContactEvent.DeleteContact(contact))
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Contact",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
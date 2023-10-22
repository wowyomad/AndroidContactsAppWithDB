package by.bsuir.vadzim.appwithroom

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = "Add Contact") },
        text = {
            TextField(
                value = state.firstName,
                onValueChange = {
                    onEvent(ContactEvent.SetFirstName(it))
                },
                label = {
                    Text(text = "first name")
                }
            )
            TextField(
                value = state.lastName,
                onValueChange = {
                    onEvent(ContactEvent.SetLastName(it))
                },
                label = {
                    Text(text = "last name")
                }
            )
            TextField(
                value = state.phoneNumber,
                onValueChange = {
                    onEvent(ContactEvent.SetPhoneNumber(it))
                },
                label = {
                    Text(text = "phone number")
                }
            )
        },
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        confirmButton = {
            Button(onClick = {
                onEvent(ContactEvent.SaveContact)
            }) {
                Text(text = "Confirm")
            }
        }
    )
}
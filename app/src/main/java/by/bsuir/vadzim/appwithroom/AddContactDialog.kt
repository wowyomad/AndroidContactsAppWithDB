package by.bsuir.vadzim.appwithroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (firstNameFocus, lastNameFocus, phoneNumberFocus) =
        FocusRequester.createRefs()
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    AlertDialog(
        modifier = modifier,
        title = { Text(text = "Add Contact") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                val focusManager = LocalFocusManager.current
                TextField(
                    modifier = Modifier
                        .focusRequester(firstNameFocus)
                        .onKeyEvent {
                            if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                                lastNameFocus.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    value = state.firstName,
                    onValueChange = {
                        onEvent(ContactEvent.SetFirstName(it))
                    },
                    label = {
                        Text(text = "first name")
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { lastNameFocus.requestFocus() }
                    )
                )
                TextField(
                    modifier = Modifier
                        .focusRequester(lastNameFocus)
                        .onKeyEvent {
                            if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                                phoneNumberFocus.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    value = state.lastName,
                    onValueChange = {
                        onEvent(ContactEvent.SetLastName(it))
                    },
                    label = {
                        Text(text = "last name")
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { phoneNumberFocus.requestFocus() }
                    )
                )
                TextField(
                    modifier = Modifier
                        .focusRequester(phoneNumberFocus)
                        .onKeyEvent {
                            if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                                focusManager.clearFocus()
                                true
                            } else {
                                false
                            }
                        },
                    shape = RoundedCornerShape(40.dp),
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    label = {
                        Text(text = "phone number")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),

                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
            }
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

package by.bsuir.vadzim.appwithroom

import android.os.Bundle
import android.text.Editable.Factory
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import by.bsuir.vadzim.appwithroom.ui.theme.AppWithRoomTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = ContactDatabase::class.java,
            name = "contacts.db"
        ).build()
    }
    private val viewModel by viewModels<ContactViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(db.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWithRoomTheme {
                val state by viewModel.state.collectAsState()
                ContactScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}
@Composable
fun SortTypeBar(
    padding: PaddingValues,
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        SortType.values().forEach {sortType ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                    .clickable(onClick = {
                        onEvent(ContactEvent.SortContacts(sortType))
                    }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.sortType == sortType,
                    onClick = {
                        onEvent(ContactEvent.SortContacts(sortType))
                    }
                )
                Text(text = sortType.name, modifier = Modifier.offset(x = (-8).dp))
            }
        }
    }
}

@Composable
fun DatabaseColumn(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {

}

@Composable
@Preview
fun DatabaseScreenPreview() {
    val contactViewModel: ContactViewModel
}

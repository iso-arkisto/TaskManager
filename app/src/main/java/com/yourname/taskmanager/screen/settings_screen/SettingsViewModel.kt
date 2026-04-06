package com.yourname.taskmanager.screen.settings_screen

import android.R
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.datastore.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DatastoreManager
): ViewModel() {
    val colorItemListState = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {
            dataStore.getStringPreference(DatastoreManager.TITLE_COLOR, "#FFB388FF")
                .collect { selectedColor ->
                    val tempColorList = ArrayList<ColorItem>()
                    ColorUtils.colorList.forEach { color ->
                        tempColorList.add(ColorItem(
                            color = color,
                            isSelected = color == selectedColor
                        ))
                    }
                    colorItemListState.value = tempColorList
                }

        }
    }

    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnItemSelected -> {
                viewModelScope.launch {
                    dataStore.saveStringPreference(event.color, DatastoreManager.TITLE_COLOR)
                }
            }
        }
    }
}
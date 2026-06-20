package com.yourname.taskmanager.screen.note_list_screen

import com.yourname.taskmanager.data.entity.NoteItem

sealed class NoteListEvent {
    data class OnShowDeleteDialog(
        val item: NoteItem
    ): NoteListEvent()

    data class OnTextSearchChange(
        val text: String
    ): NoteListEvent()

    object OnCancelItem: NoteListEvent()

    data class OnItemClick(
        val route: String
    ): NoteListEvent()
}
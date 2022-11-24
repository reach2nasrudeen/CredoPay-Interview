package com.interview.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.interview.app.model.CharacterItem

@Database(
    entities = [
        CharacterItem::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ThronesDB: RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
}
package com.interview.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interview.app.model.CharacterItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterItem: CharacterItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterItems: List<CharacterItem>)

    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<CharacterItem>?>
}
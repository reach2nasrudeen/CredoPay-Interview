package com.interview.app.model

import androidx.room.Entity
import java.io.Serializable

@Entity(primaryKeys = ["id"], tableName = "character")
data class CharacterItem(
    var family: String = "",
    var firstName: String = "",
    var fullName: String = "",
    var id: Int = 0,
    var image: String = "",
    var imageUrl: String = "",
    var lastName: String = "",
    var title: String = ""
) : Serializable
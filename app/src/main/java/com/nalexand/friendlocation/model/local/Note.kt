package com.nalexand.friendlocation.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	@ColumnInfo(name = "user_id")
	val user_id: String,
	val note: String,
	val header: String,
	val date: Long
)
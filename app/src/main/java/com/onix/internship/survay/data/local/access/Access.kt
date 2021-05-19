package com.onix.internship.survay.data.local.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class Access(
    @PrimaryKey(autoGenerate = true)
    val accessId: Long,

    val accessUserId: Long,

    val accessTestId: Long,

    val enable: Int
)
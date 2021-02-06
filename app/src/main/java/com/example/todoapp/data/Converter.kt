package com.example.todoapp.data

import androidx.room.TypeConverter
import com.example.todoapp.data.models.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

    fun fromString(priority: String): Priority {
        return when (priority) {
            "High" -> Priority.HIGH
            "Medium" -> Priority.MEDIUM
            "Low" -> Priority.LOW
            else -> Priority.MEDIUM
        }
    }
}
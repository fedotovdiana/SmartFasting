package com.itis.group11801.fedotova.smartfasting.data.mappers

import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.mapper.mapTrackerNoteLocalToTrackerNote
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.mapper.mapTrackerNoteToTrackerNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.model.TrackerNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TrackerNoteMapperTest {

    private val date = Date()

    private val trackerNoteLocal = TrackerNoteLocal(0, 1, date)

    private val trackerNote = TrackerNote(1, date)


    @Test
    fun `map tracker note local to tracker note`() {
        assertEquals(trackerNote, mapTrackerNoteLocalToTrackerNote(trackerNoteLocal))
    }

    @Test
    fun `map tracker note to tracker note local`() {
        assertEquals(trackerNoteLocal, mapTrackerNoteToTrackerNoteLocal(trackerNote))
    }
}
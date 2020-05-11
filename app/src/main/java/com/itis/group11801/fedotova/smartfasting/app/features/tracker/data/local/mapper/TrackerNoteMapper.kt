package com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.mapper

import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.model.TrackerNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote

fun mapTrackerNoteLocalToTrackerNote(trackerNoteLocal: TrackerNoteLocal): TrackerNote {
    return with(trackerNoteLocal) {
        TrackerNote(time, date)
    }
}

fun mapTrackerNoteToTrackerNoteLocal(trackerNote: TrackerNote): TrackerNoteLocal {
    return with(trackerNote) {
        TrackerNoteLocal(0, time, date)
    }
}
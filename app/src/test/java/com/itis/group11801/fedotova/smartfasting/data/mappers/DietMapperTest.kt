package com.itis.group11801.fedotova.smartfasting.data.mappers

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.mapper.mapDietRemoteToDiet
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import junit.framework.Assert.assertEquals
import org.junit.Test

class DietMapperTest {

    private val dietRemote = DietRemote(1, "title")

    private val diet = Diet(1, "title")

    @Test
    fun `map diet remote to diet`() {
        assertEquals(diet, mapDietRemoteToDiet(dietRemote))
    }
}
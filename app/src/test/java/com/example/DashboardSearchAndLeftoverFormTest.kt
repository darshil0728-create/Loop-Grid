package com.example

import com.example.data.DigitalResourcePassport
import com.example.data.DrpStatus
import com.example.data.EnterpriseRole
import com.example.data.getInitialEnterpriseDrps
import com.example.ui.components.ConnectedEnterpriseInfo
import com.example.ui.components.DashboardSearchFilter
import com.example.ui.components.LeftoverMaterialListing
import com.example.ui.components.LeftoverMaterialTypes
import com.example.ui.components.MaterialAvailabilityOptions
import com.example.ui.components.SampleConnectedEnterprises
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DashboardSearchAndLeftoverFormTest {

    @Test
    fun testDashboardSearchFilterAvailableResources() {
        val allPassports = getInitialEnterpriseDrps()
        val availableOnly = allPassports.filter { !it.status.isOutdated }

        // Test search query filtering by metal/material name
        val query = "mild steel"
        val filtered = availableOnly.filter { drp ->
            drp.materialName.lowercase().contains(query) ||
                    drp.materialCategory.lowercase().contains(query) ||
                    drp.drpId.lowercase().contains(query)
        }

        assertTrue("Should find at least one mild steel resource", filtered.isNotEmpty())
        assertTrue("Found resource should contain mild steel in name or category",
            filtered.all { it.materialName.contains("Mild Steel", ignoreCase = true) || it.materialCategory.contains("Mild Steel", ignoreCase = true) }
        )
    }

    @Test
    fun testDashboardSearchFilterConnectedEnterprises() {
        val enterprises = SampleConnectedEnterprises
        assertTrue("Sample enterprises list should not be empty", enterprises.isNotEmpty())

        val query = "pune"
        val filteredByLocation = enterprises.filter { ent ->
            ent.enterpriseName.lowercase().contains(query) ||
                    ent.location.lowercase().contains(query) ||
                    ent.primaryResources.any { it.lowercase().contains(query) }
        }

        assertTrue("Should find enterprises in Pune", filteredByLocation.isNotEmpty())
        assertTrue("Enterprises should match Pune location",
            filteredByLocation.all { it.location.contains("Pune", ignoreCase = true) }
        )
    }

    @Test
    fun testDashboardSearchFilterScope() {
        assertEquals("All Matches", DashboardSearchFilter.ALL.label)
        assertEquals("Available Resources", DashboardSearchFilter.RESOURCES.label)
        assertEquals("Connected Enterprises", DashboardSearchFilter.ENTERPRISES.label)
    }

    @Test
    fun testLeftoverMaterialFormValidation() {
        // Test Material Types list
        assertTrue("Material types list should contain standard industrial scrap", LeftoverMaterialTypes.isNotEmpty())
        assertTrue("Contains ferrous metals", LeftoverMaterialTypes.any { it.contains("Ferrous Scrap", ignoreCase = true) })
        assertTrue("Contains non-ferrous metals", LeftoverMaterialTypes.any { it.contains("Non-Ferrous", ignoreCase = true) })
        assertTrue("Contains polymers", LeftoverMaterialTypes.any { it.contains("Polymers", ignoreCase = true) })

        // Test Material Availability options
        assertTrue("Availability options list should have immediate availability", MaterialAvailabilityOptions.isNotEmpty())
        assertTrue("Contains Immediate pickup", MaterialAvailabilityOptions.any { it.contains("Immediate", ignoreCase = true) })

        // Validation logic tests
        fun validateForm(
            materialType: String,
            specificDescription: String,
            quantityInput: String,
            locationInput: String,
            availability: String
        ): Boolean {
            if (materialType.isBlank()) return false
            if (specificDescription.trim().length < 3) return false
            val qty = quantityInput.trim().toDoubleOrNull()
            if (qty == null || qty <= 0.0) return false
            if (locationInput.trim().length < 3) return false
            if (availability.isBlank()) return false
            return true
        }

        // Valid form
        assertTrue(
            validateForm(
                materialType = LeftoverMaterialTypes.first(),
                specificDescription = "Mild Steel CNC Turnings",
                quantityInput = "18.5",
                locationInput = "MIDC Bhosari, Pune",
                availability = MaterialAvailabilityOptions.first()
            )
        )

        // Invalid description (too short)
        assertFalse(
            validateForm(
                materialType = LeftoverMaterialTypes.first(),
                specificDescription = "MS",
                quantityInput = "18.5",
                locationInput = "MIDC Bhosari, Pune",
                availability = MaterialAvailabilityOptions.first()
            )
        )

        // Invalid quantity (negative or zero or non-numeric)
        assertFalse(
            validateForm(
                materialType = LeftoverMaterialTypes.first(),
                specificDescription = "Mild Steel CNC Turnings",
                quantityInput = "-5.0",
                locationInput = "MIDC Bhosari, Pune",
                availability = MaterialAvailabilityOptions.first()
            )
        )
        assertFalse(
            validateForm(
                materialType = LeftoverMaterialTypes.first(),
                specificDescription = "Mild Steel CNC Turnings",
                quantityInput = "abc",
                locationInput = "MIDC Bhosari, Pune",
                availability = MaterialAvailabilityOptions.first()
            )
        )

        // Invalid location (too short)
        assertFalse(
            validateForm(
                materialType = LeftoverMaterialTypes.first(),
                specificDescription = "Mild Steel CNC Turnings",
                quantityInput = "10.0",
                locationInput = "",
                availability = MaterialAvailabilityOptions.first()
            )
        )
    }

    @Test
    fun testLeftoverMaterialListingCreation() {
        val listing = LeftoverMaterialListing(
            materialType = "Ferrous Scrap (Mild Steel, Turnings, Stampings)",
            specificDescription = "Stamped plate cuttings",
            quantityValue = 24.0,
            quantityUnit = "Tons",
            location = "Chakan Phase II, Pune",
            availability = "Immediate / Ready for Pickup",
            purityGrade = "IS 2062 Certified",
            notes = "Segregated and bundled, zero grease"
        )

        assertEquals("Ferrous Scrap (Mild Steel, Turnings, Stampings)", listing.materialType)
        assertEquals("Stamped plate cuttings", listing.specificDescription)
        assertEquals(24.0, listing.quantityValue, 0.001)
        assertEquals("Tons", listing.quantityUnit)
        assertEquals("Chakan Phase II, Pune", listing.location)
        assertEquals("Immediate / Ready for Pickup", listing.availability)
    }
}

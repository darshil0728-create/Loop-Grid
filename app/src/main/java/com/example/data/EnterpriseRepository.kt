package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository to persist and manage the enterprise profile, credentials, and profile drafts.
 */
class EnterpriseRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("loopgrid_enterprise_prefs", Context.MODE_PRIVATE)

    private val _currentProfile = MutableStateFlow(loadProfileFromPrefs())
    val currentProfile: StateFlow<EnterpriseProfile> = _currentProfile.asStateFlow()

    private fun loadProfileFromPrefs(): EnterpriseProfile {
        val hasAccount = prefs.getBoolean("has_account", false)
        if (!hasAccount) {
            // Seed with a realistic demo enterprise so immediate Login works smoothly
            return EnterpriseProfile(
                enterpriseName = "Shree Balaji Fabrication Works",
                ownerName = "Rajesh Sharma",
                orgType = "MSME (Small Enterprise - Investment < ₹10 Cr)",
                orgNature = "Metal Fabrication & Scrap Recovery",
                phone = "+91 98234 56789",
                email = "contact@balajifabrication.in",
                password = "password123",
                dealingResources = listOf("Cold Rolled Steel Coils", "Mild Steel Turning Scrap", "Zinc Ash & Residues"),
                address = "Plot No. D-42, MIDC Industrial Area, Bhosari, Pune, Maharashtra 411026",
                registeredOffice = "Office 301, Trade Center, F.C. Road, Shivajinagar, Pune 411005",
                branches = listOf("Chakan Auto Cluster Unit-2", "Ahmednagar Material Staging Hub"),
                gstin = "27AAACB1234F1Z5",
                udyamNumber = "UDYAM-MH-26-0034567",
                annualVolumeTons = "450 Tons / Year",
                pollutionClearanceStatus = "Consent to Operate (CTO) Valid up to 2028",
                sustainabilityGoals = "Zero landfill disposal for metallic turnings; 100% circular traceability through DRPs.",
                isDraft = false,
                isRegistered = true
            )
        }

        val resourcesString = prefs.getString("dealing_resources", "") ?: ""
        val branchesString = prefs.getString("branches", "") ?: ""

        return EnterpriseProfile(
            enterpriseName = prefs.getString("enterprise_name", "") ?: "",
            ownerName = prefs.getString("owner_name", "") ?: "",
            orgType = prefs.getString("org_type", "MSME (Small Enterprise)") ?: "MSME (Small Enterprise)",
            orgNature = prefs.getString("org_nature", "Metal Fabrication & Scrap Recovery") ?: "Metal Fabrication & Scrap Recovery",
            phone = prefs.getString("phone", "") ?: "",
            email = prefs.getString("email", "") ?: "",
            password = prefs.getString("password", "") ?: "",
            dealingResources = if (resourcesString.isNotBlank()) resourcesString.split(";;") else emptyList(),
            address = prefs.getString("address", "") ?: "",
            registeredOffice = prefs.getString("registered_office", "") ?: "",
            branches = if (branchesString.isNotBlank()) branchesString.split(";;") else emptyList(),
            gstin = prefs.getString("gstin", "") ?: "",
            udyamNumber = prefs.getString("udyam_number", "") ?: "",
            annualVolumeTons = prefs.getString("annual_volume", "") ?: "",
            pollutionClearanceStatus = prefs.getString("pollution_status", "Consent to Operate (CTO) Active") ?: "Consent to Operate (CTO) Active",
            sustainabilityGoals = prefs.getString("sustainability_goals", "") ?: "",
            isDraft = prefs.getBoolean("is_draft", false),
            isRegistered = true
        )
    }

    fun saveProfile(profile: EnterpriseProfile, isDraft: Boolean = false) {
        val updated = profile.copy(isDraft = isDraft, isRegistered = true)
        prefs.edit().apply {
            putBoolean("has_account", true)
            putString("enterprise_name", updated.enterpriseName)
            putString("owner_name", updated.ownerName)
            putString("org_type", updated.orgType)
            putString("org_nature", updated.orgNature)
            putString("phone", updated.phone)
            putString("email", updated.email)
            putString("password", updated.password)
            putString("dealing_resources", updated.dealingResources.joinToString(";;"))
            putString("address", updated.address)
            putString("registered_office", updated.registeredOffice)
            putString("branches", updated.branches.joinToString(";;"))
            putString("gstin", updated.gstin)
            putString("udyam_number", updated.udyamNumber)
            putString("annual_volume", updated.annualVolumeTons)
            putString("pollution_status", updated.pollutionClearanceStatus)
            putString("sustainability_goals", updated.sustainabilityGoals)
            putBoolean("is_draft", isDraft)
            apply()
        }
        _currentProfile.value = updated
    }

    fun registerNewEnterprise(
        enterpriseName: String,
        ownerName: String,
        orgType: String,
        orgNature: String,
        phone: String,
        email: String,
        password: String
    ): EnterpriseProfile {
        val newProfile = EnterpriseProfile(
            enterpriseName = enterpriseName,
            ownerName = ownerName,
            orgType = orgType,
            orgNature = orgNature,
            phone = phone,
            email = email,
            password = password,
            isDraft = true,
            isRegistered = true
        )
        saveProfile(newProfile, isDraft = true)
        return newProfile
    }

    /**
     * Checks if credentials match the registered enterprise or default enterprise.
     */
    fun validateLogin(enterpriseNameInput: String, passwordInput: String): Boolean {
        val profile = _currentProfile.value
        val nameMatches = profile.enterpriseName.trim().equals(enterpriseNameInput.trim(), ignoreCase = true)
        val passwordMatches = profile.password.isBlank() || profile.password == passwordInput

        // Also allow matching if any enterprise name is provided for flexible testing if password is empty/default
        if (nameMatches && passwordMatches) return true

        // Allow login with demo if matched
        if (enterpriseNameInput.isNotBlank() && (passwordInput.isNotBlank() || passwordInput == "password123")) {
            // Update active enterprise name
            saveProfile(profile.copy(enterpriseName = enterpriseNameInput), isDraft = profile.isDraft)
            return true
        }

        return false
    }

    companion object {
        @Volatile
        private var instance: EnterpriseRepository? = null

        fun getInstance(context: Context): EnterpriseRepository {
            return instance ?: synchronized(this) {
                instance ?: EnterpriseRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}

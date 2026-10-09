package com.example.data

/**
 * Enterprise profile data model holding initial registration credentials
 * and extended profile information filled in the Dashboard.
 */
data class EnterpriseProfile(
    // Initial Sign Up / Registration Fields
    val enterpriseName: String = "",
    val ownerName: String = "",
    val orgType: String = "MSME (Small Enterprise)",
    val orgNature: String = "Metal Fabrication & Scrap Recovery",
    val phone: String = "",
    val email: String = "",
    val password: String = "",

    // Extended Details ("Complete Your Profile" in Dashboard)
    val dealingResources: List<String> = emptyList(),
    val address: String = "",
    val registeredOffice: String = "",
    val branches: List<String> = emptyList(),
    val gstin: String = "",
    val udyamNumber: String = "",
    val annualVolumeTons: String = "",
    val pollutionClearanceStatus: String = "Consent to Operate (CTO) Active",
    val sustainabilityGoals: String = "",

    // Profile state flags
    val isDraft: Boolean = true,
    val isRegistered: Boolean = false
) {
    /**
     * Calculates the profile completion percentage based on filled fields.
     */
    fun calculateCompletionPercent(): Int {
        var completedWeight = 0
        val totalWeight = 100

        // Basic sign up fields (40%)
        if (enterpriseName.isNotBlank()) completedWeight += 10
        if (ownerName.isNotBlank()) completedWeight += 10
        if (phone.isNotBlank()) completedWeight += 10
        if (email.isNotBlank()) completedWeight += 10

        // Extended business details (60%)
        if (dealingResources.isNotEmpty()) completedWeight += 15
        if (address.isNotBlank()) completedWeight += 15
        if (registeredOffice.isNotBlank()) completedWeight += 10
        if (branches.isNotEmpty() || gstin.isNotBlank()) completedWeight += 10
        if (annualVolumeTons.isNotBlank() || sustainabilityGoals.isNotBlank()) completedWeight += 10

        return completedWeight.coerceIn(0, totalWeight)
    }
}

/**
 * Predefined options for "What type of organisation is it"
 */
val OrganizationTypes = listOf(
    "MSME (Micro Enterprise - Investment < ₹1 Cr)",
    "MSME (Small Enterprise - Investment < ₹10 Cr)",
    "MSME (Medium Enterprise - Investment < ₹50 Cr)",
    "Private Limited Company (Pvt Ltd)",
    "Public Limited Company",
    "Sole Proprietorship",
    "Partnership Firm",
    "Limited Liability Partnership (LLP)",
    "Industrial Cooperative / Cluster Association"
)

/**
 * Inbuilt options for "Nature of organisation"
 */
val OrganizationNatures = listOf(
    "Metal Fabrication & Scrap Recovery",
    "Foundry, Forging & Casting",
    "Automotive & Ancillary Engineering",
    "Chemical & Solvent Processing",
    "Textile, Yarn & Fabric Recycling",
    "Plastics, Polymers & Compounding",
    "Rubber & Tyre Pyrolysis",
    "Electronic & E-Waste Processing",
    "Construction Aggregates & Slag Utilisation",
    "Agro-Processing & Biomass By-products",
    "General Industrial Manufacturing & Assembly"
)

/**
 * Common industrial resource categories for quick chip selection
 */
val PopularIndustrialResources = listOf(
    "Cold Rolled Steel Coils",
    "Aluminum Dross & Slag",
    "Copper Millberry Wire",
    "Foundry Silica Sand",
    "Zinc Ash & Residues",
    "Recycled HDPE Granules",
    "Cotton Comber Waste",
    "Spent Caustic & Solvents",
    "Mild Steel Turning Scrap",
    "Brass Dross & Swarf"
)

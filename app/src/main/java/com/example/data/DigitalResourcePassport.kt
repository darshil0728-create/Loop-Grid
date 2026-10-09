package com.example.data

/**
 * Lifecycle status of a Digital Resource Passport (DRP):
 * - AVAILABLE: Currently active on the platform and available for matching/ordering
 * - ALLOTTED: Matched with an offtaker, logistics in progress
 * - OUTDATED_TRANSACTED: Transaction completed, payment settled; passport is retired from active marketplace
 *   and securely archived in the DRP History.
 */
enum class DrpStatus(
    val label: String,
    val isOutdated: Boolean
) {
    AVAILABLE("Available in Marketplace", false),
    ALLOTTED("Transit & Allotted", false),
    OUTDATED_TRANSACTED("Outdated (Transacted & Settled)", true);
}

/**
 * Complete Digital Resource Passport data model.
 */
data class DigitalResourcePassport(
    val drpId: String,
    val materialName: String,
    val materialCategory: String,
    val quantityValue: Double,
    val quantityUnit: String, // "Tons", "Kg", etc.
    val enterpriseName: String,
    val originDistrict: String,
    val originState: String,
    val purityGrade: String,
    val status: DrpStatus,
    val issuanceDate: String,
    val transactedDate: String? = null,
    val buyerEnterprise: String? = null,
    val transactionAmount: String? = null,
    val transitPartnerAssigned: String? = null,
    val notes: String = ""
)

/**
 * Initial sample DRPs for the enterprise and platform inventory
 */
fun getInitialEnterpriseDrps(): List<DigitalResourcePassport> = listOf(
    // Currently Available DRPs
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-9041",
        materialName = "Cold Rolled Steel Trimmings (CRCA)",
        materialCategory = "Ferrous Metals & Scrap",
        quantityValue = 38.5,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "IS 513 Grade D (99.2% Metallic)",
        status = DrpStatus.AVAILABLE,
        issuanceDate = "04 Oct 2026",
        notes = "Clean sheared edges, unpainted, zero heavy oil contamination."
    ),
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-8812",
        materialName = "Zinc Residue & Skimmings",
        materialCategory = "Non-Ferrous Residues",
        quantityValue = 14.2,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "Grade-B Galvanizing Dross (72% Zn)",
        status = DrpStatus.AVAILABLE,
        issuanceDate = "06 Oct 2026",
        notes = "Dry stored, moisture < 1.5%, sampled per SPCB guidelines."
    ),
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-9420",
        materialName = "Mild Steel Punching Scrap",
        materialCategory = "Ferrous Metals & Scrap",
        quantityValue = 22.0,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "IS 2062 Structural Plate Cut-outs",
        status = DrpStatus.AVAILABLE,
        issuanceDate = "08 Oct 2026",
        notes = "Direct melt ready, 6mm-12mm thickness."
    ),

    // Outdated DRPs (Transacted and archived in DRP History)
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-7210",
        materialName = "High Grade Copper Wire Scrap (Millberry)",
        materialCategory = "Non-Ferrous Metals",
        quantityValue = 8.4,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "Berry (99.9% Cu Purity)",
        status = DrpStatus.OUTDATED_TRANSACTED,
        issuanceDate = "15 Sep 2026",
        transactedDate = "22 Sep 2026",
        buyerEnterprise = "Maharashtra Metal Recyclers & Smelters",
        transactionAmount = "₹62,16,000",
        transitPartnerAssigned = "Apex Green Logistics & Freight (GJ-01-AX-9921)",
        notes = "Fulfilled via LoopGrid Marketplace. Escrow settlement paid in 5 working days."
    ),
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-6840",
        materialName = "Aluminium 6063 Extrusion End-Cuts",
        materialCategory = "Non-Ferrous Scrap",
        quantityValue = 16.0,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "Clean Architectural Extrusion",
        status = DrpStatus.OUTDATED_TRANSACTED,
        issuanceDate = "02 Sep 2026",
        transactedDate = "11 Sep 2026",
        buyerEnterprise = "Kalyani Smelters & Alloys Ltd",
        transactionAmount = "₹31,68,000",
        transitPartnerAssigned = "Western Inter-State Fleet Express",
        notes = "Transaction closed. Weighbridge slip verified, DRP passport retired."
    ),
    DigitalResourcePassport(
        drpId = "DRP-MH-2026-5510",
        materialName = "Foundry Grade Cast Iron Borings",
        materialCategory = "Ferrous Metals & Scrap",
        quantityValue = 45.0,
        quantityUnit = "Tons",
        enterpriseName = "Shree Balaji Fabrication Works",
        originDistrict = "Pune",
        originState = "Maharashtra",
        purityGrade = "Low Sulphur Borings",
        status = DrpStatus.OUTDATED_TRANSACTED,
        issuanceDate = "10 Aug 2026",
        transactedDate = "19 Aug 2026",
        buyerEnterprise = "Shivaji Foundry Cluster Co-op",
        transactionAmount = "₹15,75,000",
        transitPartnerAssigned = "Apex Green Logistics & Freight",
        notes = "Full batch transacted and processed. Payment settled via escrow."
    ),

    // Other Enterprises on Platform (for global search discovery)
    DigitalResourcePassport(
        drpId = "DRP-GJ-2026-1049",
        materialName = "Recycled Polypropylene Granules (rPP)",
        materialCategory = "Polymers & Plastics",
        quantityValue = 28.0,
        quantityUnit = "Tons",
        enterpriseName = "Gujarat Polymer Converters Pvt Ltd",
        originDistrict = "Surat",
        originState = "Gujarat",
        purityGrade = "MFI 12 Injection Moulding Grade",
        status = DrpStatus.AVAILABLE,
        issuanceDate = "05 Oct 2026",
        notes = "Washed, double-degassed pellets."
    ),
    DigitalResourcePassport(
        drpId = "DRP-KA-2026-4421",
        materialName = "Brass Honey Scrap (Honey / Ocean)",
        materialCategory = "Non-Ferrous Metals",
        quantityValue = 11.5,
        quantityUnit = "Tons",
        enterpriseName = "Bangalore Precision Valve Fabricators",
        originDistrict = "Bengaluru",
        originState = "Karnataka",
        purityGrade = "IS 319 Free Cutting Brass",
        status = DrpStatus.AVAILABLE,
        issuanceDate = "07 Oct 2026",
        notes = "Unmixed turning scrap from CNC manufacturing."
    )
)

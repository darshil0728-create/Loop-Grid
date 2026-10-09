package com.example.data

enum class NotificationCategory(val title: String) {
    ALL("All"),
    MATERIAL_MATCH("Material Matches"),
    DRP_ACTION("DRP Actions"),
    PROFILE_RESOURCE("Profile & Resources")
}

data class DashboardNotification(
    val id: String,
    val title: String,
    val description: String,
    val category: NotificationCategory,
    val timestamp: String,
    val isRead: Boolean = false,
    val actionLabel: String? = null,
    val isUrgent: Boolean = false
)

fun getInitialDashboardNotifications(): List<DashboardNotification> {
    return listOf(
        DashboardNotification(
            id = "notif_match_01",
            title = "High-Volume Offtake Match Found!",
            description = "Apex Heavy Forgings (Pune MIDC) matched with your Mild Steel Turning Scrap. Estimated lot: 35 Tons @ ₹36,500/Ton.",
            category = NotificationCategory.MATERIAL_MATCH,
            timestamp = "10m ago",
            isRead = false,
            actionLabel = "Review Match Offer",
            isUrgent = true
        ),
        DashboardNotification(
            id = "notif_drp_01",
            title = "DRP Action Required: Weighbridge Clearance",
            description = "Digital Resource Passport DRP-MH-2026-9041 is awaiting weighbridge slip confirmation to seal outbound logistics transit.",
            category = NotificationCategory.DRP_ACTION,
            timestamp = "45m ago",
            isRead = false,
            actionLabel = "Upload Slip & Verify",
            isUrgent = true
        ),
        DashboardNotification(
            id = "notif_match_02",
            title = "Recurring Purchase Inquiry Received",
            description = "Mahindra Sona Ancillaries cluster unit requested a recurring bi-weekly supply of Cold Rolled Steel Trimmings.",
            category = NotificationCategory.MATERIAL_MATCH,
            timestamp = "3h ago",
            isRead = false,
            actionLabel = "Connect with Buyer",
            isUrgent = false
        ),
        DashboardNotification(
            id = "notif_profile_01",
            title = "Profile Verification Complete",
            description = "Your Udyam Registration (UDYAM-MH-26-0034567) and SPCB CTO clearance have been verified for cluster aggregation.",
            category = NotificationCategory.PROFILE_RESOURCE,
            timestamp = "Yesterday",
            isRead = true,
            actionLabel = "View Verified Badge",
            isUrgent = false
        ),
        DashboardNotification(
            id = "notif_resource_01",
            title = "Scrap Benchmark Price Update",
            description = "Secondary Zinc Ash and Aluminum dross benchmark rates rose by +4.2% across Maharashtra industrial belts.",
            category = NotificationCategory.PROFILE_RESOURCE,
            timestamp = "2d ago",
            isRead = true,
            actionLabel = "View Price Index",
            isUrgent = false
        )
    )
}

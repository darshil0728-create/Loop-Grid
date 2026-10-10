package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate950

/**
 * Standard Leftover Material Types available for circular listing.
 */
val LeftoverMaterialTypes = listOf(
    "Ferrous Scrap (Mild Steel, Turnings, Stampings)",
    "Non-Ferrous Metals (Aluminium, Copper, Brass)",
    "Polymers & Plastics (HDPE, LDPE, Polypropylene)",
    "Industrial Slag, Ash & Mineral Residues",
    "Textile Lint & Fabric Cutting Scraps",
    "Spent Foundry Sand & Refractory Waste",
    "Chemical By-products, Caustic & Solvents",
    "Wood, Biomass & Pallet By-products",
    "Other Secondary Industrial Stream"
)

/**
 * Availability timelines for leftover materials.
 */
val MaterialAvailabilityOptions = listOf(
    "Immediate / Ready for Pickup",
    "Available Within 48 Hours",
    "Available This Weekend",
    "Recurring Weekly Stream",
    "Recurring Monthly Generation"
)

/**
 * Standard Units of Quantity.
 */
val QuantityUnits = listOf("Tons", "Kg", "Metric MT", "Barrels (200L)", "Truckloads")

/**
 * Leftover Material Form Data Model.
 */
data class LeftoverMaterialListing(
    val materialType: String,
    val specificDescription: String,
    val quantityValue: Double,
    val quantityUnit: String,
    val location: String,
    val availability: String,
    val estimatedPricePerUnit: String = "",
    val purityGrade: String = "",
    val notes: String = ""
)

/**
 * Form component that allows businesses to list leftover materials with fields for
 * material type, quantity, location, and availability, including basic input validation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListLeftoverMaterialForm(
    isDarkTheme: Boolean,
    initialLocation: String = "Pune Industrial Area (MIDC)",
    onDismiss: () -> Unit,
    onSubmitListing: (LeftoverMaterialListing) -> Unit,
    modifier: Modifier = Modifier
) {
    // Form fields
    var materialType by remember { mutableStateOf(LeftoverMaterialTypes.first()) }
    var specificDescription by remember { mutableStateOf("") }
    var quantityInput by remember { mutableStateOf("") }
    var quantityUnit by remember { mutableStateOf("Tons") }
    var locationInput by remember { mutableStateOf(initialLocation) }
    var availabilityOption by remember { mutableStateOf(MaterialAvailabilityOptions.first()) }
    var purityGradeInput by remember { mutableStateOf("Standard Industrial Grade") }
    var notesInput by remember { mutableStateOf("") }

    // Dropdown expansion states
    var materialTypeExpanded by remember { mutableStateOf(false) }
    var availabilityExpanded by remember { mutableStateOf(false) }
    var unitExpanded by remember { mutableStateOf(false) }

    // Validation error states
    var materialTypeError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }
    var locationError by remember { mutableStateOf<String?>(null) }
    var availabilityError by remember { mutableStateOf<String?>(null) }

    var submittedSuccess by remember { mutableStateOf(false) }

    // Validation logic
    fun validate(): Boolean {
        var isValid = true

        if (materialType.isBlank()) {
            materialTypeError = "Please select a material type"
            isValid = false
        } else {
            materialTypeError = null
        }

        if (specificDescription.trim().length < 3) {
            descriptionError = "Please specify material description (at least 3 characters)"
            isValid = false
        } else {
            descriptionError = null
        }

        val qty = quantityInput.trim().toDoubleOrNull()
        if (qty == null || qty <= 0.0) {
            quantityError = "Please enter a valid positive quantity (e.g. 15.5)"
            isValid = false
        } else {
            quantityError = null
        }

        if (locationInput.trim().length < 3) {
            locationError = "Please enter plant address, district, or MIDC cluster"
            isValid = false
        } else {
            locationError = null
        }

        if (availabilityOption.isBlank()) {
            availabilityError = "Please choose material availability"
            isValid = false
        } else {
            availabilityError = null
        }

        return isValid
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = modifier
                .widthIn(max = 520.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(22.dp)
                )
                .background(if (isDarkTheme) DarkSurfaceCard else LightSurfaceCard)
                .padding(24.dp)
                .testTag("list_leftover_material_form_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Scale,
                                contentDescription = null,
                                tint = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "LIST LEFTOVER MATERIAL",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                            )
                            Text(
                                text = "Create Marketplace & DRP Circular Listing",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("btn_close_leftover_form")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                if (submittedSuccess) {
                    // Success View
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isDarkTheme) Color(0xFF132A22) else Color(0xFFF0FDF4))
                            .border(1.dp, EmeraldPrimary, RoundedCornerShape(16.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                                tint = EmeraldLight,
                                modifier = Modifier.size(44.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Material Successfully Listed!",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = EmeraldLight
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Your leftover listing for $specificDescription ($quantityInput $quantityUnit) at $locationInput is now live on the circular exchange.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Slate200 else Color(0xFF334155),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onDismiss,
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("btn_done_leftover_form")
                            ) {
                                Text(
                                    text = "Return to Dashboard",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate950
                                )
                            }
                        }
                    }
                } else {
                    // 1. MATERIAL TYPE (Dropdown & Selector)
                    Text(
                        text = "1. MATERIAL TYPE *",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    ExposedDropdownMenuBox(
                        expanded = materialTypeExpanded,
                        onExpandedChange = { materialTypeExpanded = !materialTypeExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = materialType,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = materialTypeExpanded) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Category,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            isError = materialTypeError != null,
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                                .testTag("dropdown_material_type"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmeraldPrimary,
                                unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                                focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                                unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = materialTypeExpanded,
                            onDismissRequest = { materialTypeExpanded = false },
                            modifier = Modifier.background(if (isDarkTheme) Color(0xFF1E293B) else Color.White)
                        ) {
                            LeftoverMaterialTypes.forEach { typeOption ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = typeOption,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 13.sp,
                                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                        )
                                    },
                                    onClick = {
                                        materialType = typeOption
                                        materialTypeExpanded = false
                                        materialTypeError = null
                                    }
                                )
                            }
                        }
                    }

                    if (materialTypeError != null) {
                        Text(
                            text = materialTypeError!!,
                            color = Color(0xFFEF4444),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Material Specific Description
                    Text(
                        text = "SPECIFIC MATERIAL / LOT DESCRIPTION *",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = specificDescription,
                        onValueChange = {
                            specificDescription = it
                            if (it.isNotBlank()) descriptionError = null
                        },
                        placeholder = {
                            Text(
                                text = "e.g. Cold Rolled Steel Punching Scrap (CRCA Grade-D)",
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
                            )
                        },
                        isError = descriptionError != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_leftover_description"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                            focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                            unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                        )
                    )
                    if (descriptionError != null) {
                        Text(
                            text = descriptionError!!,
                            color = Color(0xFFEF4444),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 2. QUANTITY & UNIT
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Column(modifier = Modifier.weight(1.3f)) {
                            Text(
                                text = "2. QUANTITY *",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = quantityInput,
                                onValueChange = {
                                    quantityInput = it
                                    if (it.isNotBlank()) quantityError = null
                                },
                                placeholder = { Text("e.g. 24.5", fontSize = 13.sp) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                isError = quantityError != null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_leftover_quantity"),
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = EmeraldPrimary,
                                    unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                                    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                    focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                                    unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                                )
                            )
                            if (quantityError != null) {
                                Text(
                                    text = quantityError!!,
                                    color = Color(0xFFEF4444),
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1.0f)) {
                            Text(
                                text = "UNIT",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp,
                                color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            ExposedDropdownMenuBox(
                                expanded = unitExpanded,
                                onExpandedChange = { unitExpanded = !unitExpanded },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = quantityUnit,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = unitExpanded) },
                                    modifier = Modifier
                                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                        .fillMaxWidth()
                                        .testTag("dropdown_quantity_unit"),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = EmeraldPrimary,
                                        unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                                        focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                        unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                        focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                                        unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                                    )
                                )

                                ExposedDropdownMenu(
                                    expanded = unitExpanded,
                                    onDismissRequest = { unitExpanded = false },
                                    modifier = Modifier.background(if (isDarkTheme) Color(0xFF1E293B) else Color.White)
                                ) {
                                    QuantityUnits.forEach { unitOpt ->
                                        DropdownMenuItem(
                                            text = { Text(unitOpt, fontFamily = PlusJakartaSans, fontSize = 13.sp) },
                                            onClick = {
                                                quantityUnit = unitOpt
                                                unitExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 3. LOCATION / DISTRICT
                    Text(
                        text = "3. LOCATION & FACILITY ADDRESS *",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = locationInput,
                        onValueChange = {
                            locationInput = it
                            if (it.isNotBlank()) locationError = null
                        },
                        placeholder = {
                            Text(
                                "e.g. Bhosari MIDC Plot D-42, Pune, Maharashtra",
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        isError = locationError != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_leftover_location"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                            focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                            unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                        )
                    )
                    if (locationError != null) {
                        Text(
                            text = locationError!!,
                            color = Color(0xFFEF4444),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 4. AVAILABILITY
                    Text(
                        text = "4. AVAILABILITY & TIMELINE *",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    ExposedDropdownMenuBox(
                        expanded = availabilityExpanded,
                        onExpandedChange = { availabilityExpanded = !availabilityExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = availabilityOption,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = availabilityExpanded) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            isError = availabilityError != null,
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                                .testTag("dropdown_material_availability"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmeraldPrimary,
                                unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                                focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                                unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = availabilityExpanded,
                            onDismissRequest = { availabilityExpanded = false },
                            modifier = Modifier.background(if (isDarkTheme) Color(0xFF1E293B) else Color.White)
                        ) {
                            MaterialAvailabilityOptions.forEach { opt ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = opt,
                                            fontFamily = PlusJakartaSans,
                                            fontSize = 13.sp,
                                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                        )
                                    },
                                    onClick = {
                                        availabilityOption = opt
                                        availabilityExpanded = false
                                        availabilityError = null
                                    }
                                )
                            }
                        }
                    }

                    if (availabilityError != null) {
                        Text(
                            text = availabilityError!!,
                            color = Color(0xFFEF4444),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Optional Notes & Purity
                    Text(
                        text = "PURITY GRADE / REMARKS (OPTIONAL)",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = purityGradeInput,
                        onValueChange = { purityGradeInput = it },
                        placeholder = { Text("e.g. Unmixed, zero heavy oil, IS 2062 certified", fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_leftover_purity"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                            focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                            unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Action buttons (Cancel / Submit)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Cancel", fontFamily = PlusJakartaSans, fontWeight = FontWeight.SemiBold)
                        }

                        Button(
                            onClick = {
                                if (validate()) {
                                    val listing = LeftoverMaterialListing(
                                        materialType = materialType,
                                        specificDescription = specificDescription.trim(),
                                        quantityValue = quantityInput.trim().toDoubleOrNull() ?: 1.0,
                                        quantityUnit = quantityUnit,
                                        location = locationInput.trim(),
                                        availability = availabilityOption,
                                        purityGrade = purityGradeInput.trim(),
                                        notes = notesInput.trim()
                                    )
                                    onSubmitListing(listing)
                                    submittedSuccess = true
                                }
                            },
                            modifier = Modifier
                                .weight(1.8f)
                                .height(48.dp)
                                .testTag("btn_submit_leftover_material"),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Slate950,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Publish Leftover Listing",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = Slate950
                            )
                        }
                    }
                }
            }
        }
    }
}

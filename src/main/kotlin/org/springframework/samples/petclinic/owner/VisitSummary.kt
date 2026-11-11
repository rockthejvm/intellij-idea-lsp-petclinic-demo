package org.springframework.samples.petclinic.owner

import java.time.LocalDate

/**
 * One row in the "Upcoming visits" table on the owner page.
 */
data class VisitSummary(
	val petName: String,
	val date: LocalDate,
	val description: String,
) {

	val label: String
		get() = "$petName: $description"

	fun isWithinDays(days: Long, today: LocalDate): Boolean = !date.isAfter(today.plusDays(days))

}

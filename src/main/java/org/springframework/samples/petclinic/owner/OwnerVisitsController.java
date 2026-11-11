package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * JSON endpoint for an owner's visits in a date range. Reference solution for Demo 4.
 */
@RestController
class OwnerVisitsController {

	private final OwnerRepository owners;

	OwnerVisitsController(OwnerRepository owners) {
		this.owners = owners;
	}

	@GetMapping("/api/owners/{ownerId}/visits")
	List<Visit> visits(@PathVariable int ownerId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
			@RequestParam(required = false) @Nullable Integer petId) {
		if (from.isAfter(to)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'from' must not be after 'to'");
		}
		Owner owner = this.owners.findById(ownerId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner " + ownerId + " not found"));
		List<Visit> visits = owner.visitsBetween(from, to);
		if (petId == null) {
			return visits;
		}
		Pet pet = owner.getPet(petId);
		if (pet == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet " + petId + " not found");
		}
		return visits.stream().filter(pet.getVisits()::contains).toList();
	}

}

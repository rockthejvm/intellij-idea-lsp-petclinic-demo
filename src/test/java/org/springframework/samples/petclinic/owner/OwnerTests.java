/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OwnerTests {

	@Test
	void addPetAddsPersistedPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Buddy");

		owner.addPet(pet);

		assertTrue(owner.getPets().contains(pet));
		assertEquals(1, owner.getPets().size());
	}

	@Test
	void addPetDoesNotAddDuplicatePet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Buddy");

		owner.addPet(pet);
		owner.addPet(pet);

		assertEquals(1, owner.getPets().size());
	}

	@Test
	void visitsBetweenIncludesBothEnds() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(7);
		pet.setName("Samantha");
		pet.addVisit(visit(LocalDate.of(2030, 3, 9), "too early"));
		pet.addVisit(visit(LocalDate.of(2030, 3, 10), "annual checkup"));
		pet.addVisit(visit(LocalDate.of(2030, 3, 11), "follow-up"));
		pet.addVisit(visit(LocalDate.of(2030, 3, 12), "dental cleaning"));
		pet.addVisit(visit(LocalDate.of(2030, 3, 13), "too late"));
		owner.addPet(pet);

		var visits = owner.visitsBetween(LocalDate.of(2030, 3, 10), LocalDate.of(2030, 3, 12));

		assertThat(visits).extracting(Visit::getDescription)
			.containsExactly("annual checkup", "follow-up", "dental cleaning");
	}

	private static Visit visit(LocalDate date, String description) {
		Visit visit = new Visit();
		visit.setDate(date);
		visit.setDescription(description);
		return visit;
	}

}

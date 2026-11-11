package org.springframework.samples.petclinic.owner;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OwnerVisitsController.class)
@DisabledInNativeImage
@DisabledInAotMode
class OwnerVisitsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private OwnerRepository owners;

	@BeforeEach
	void init() {
		Owner owner = new Owner();
		Pet samantha = pet(7, "Samantha");
		samantha.addVisit(visit(LocalDate.of(2030, 3, 10), "annual checkup"));
		Pet max = pet(8, "Max");
		max.addVisit(visit(LocalDate.of(2030, 3, 12), "dental cleaning"));
		max.addVisit(visit(LocalDate.of(2030, 4, 1), "outside the range"));
		owner.addPet(samantha);
		owner.addPet(max);
		given(this.owners.findById(6)).willReturn(Optional.of(owner));
	}

	@Test
	void returnsVisitsInRangeBothEndsIncluded() throws Exception {
		mockMvc.perform(get("/api/owners/6/visits").param("from", "2030-03-10").param("to", "2030-03-12"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].description").value("annual checkup"))
			.andExpect(jsonPath("$[1].description").value("dental cleaning"));
	}

	@Test
	void filtersByPet() throws Exception {
		mockMvc
			.perform(get("/api/owners/6/visits").param("from", "2030-03-01")
				.param("to", "2030-03-31")
				.param("petId", "8"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(1))
			.andExpect(jsonPath("$[0].description").value("dental cleaning"));
	}

	@Test
	void unknownPetIs404() throws Exception {
		mockMvc
			.perform(get("/api/owners/6/visits").param("from", "2030-03-01")
				.param("to", "2030-03-31")
				.param("petId", "99"))
			.andExpect(status().isNotFound());
	}

	@Test
	void unknownOwnerIs404() throws Exception {
		mockMvc.perform(get("/api/owners/42/visits").param("from", "2030-03-01").param("to", "2030-03-31"))
			.andExpect(status().isNotFound());
	}

	private static Pet pet(int id, String name) {
		Pet pet = new Pet();
		pet.setId(id);
		pet.setName(name);
		return pet;
	}

	private static Visit visit(LocalDate date, String description) {
		Visit visit = new Visit();
		visit.setDate(date);
		visit.setDescription(description);
		return visit;
	}

}

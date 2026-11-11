package org.springframework.samples.petclinic.notification;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;

/**
 * Tells an owner that a visit was booked.
 */
public interface VisitNotifier {

	void visitBooked(Owner owner, Visit visit);

}

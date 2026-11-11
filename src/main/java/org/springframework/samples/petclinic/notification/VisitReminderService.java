package org.springframework.samples.petclinic.notification;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Service;

/**
 * Sends a reminder the day before a visit.
 */
@Service
public class VisitReminderService {

	private final VisitNotifier notifier;

	public VisitReminderService(@Qualifier("smsVisitNotifier") VisitNotifier notifier) {
		this.notifier = notifier;
	}

	public void remind(Owner owner, Visit visit) {
		notifier.visitBooked(owner, visit);
	}

}

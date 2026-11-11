package org.springframework.samples.petclinic.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Service;

/**
 * Sends a reminder the day before a visit.
 */
@Service
public class VisitReminderService {

	@Autowired
	@Qualifier("smsVisitNotifer")
	private VisitNotifier notifier;

	public void remind(Owner owner, Visit visit) {
		notifier.visitBooked(owner, visit);
	}

}

package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Component;

@Component
class EmailVisitNotifier implements VisitNotifier {

	private static final Logger log = LoggerFactory.getLogger(EmailVisitNotifier.class);

	@Override
	public void visitBooked(Owner owner, Visit visit) {
		log.info("Email to {} {}: visit booked for {}", owner.getFirstName(), owner.getLastName(), visit.getDate());
	}

}

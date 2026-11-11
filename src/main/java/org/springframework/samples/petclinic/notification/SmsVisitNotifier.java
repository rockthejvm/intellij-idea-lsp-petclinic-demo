package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Component;

@Component
class SmsVisitNotifier implements VisitNotifier {

	private static final Logger log = LoggerFactory.getLogger(SmsVisitNotifier.class);

	@Override
	public void visitBooked(Owner owner, Visit visit) {
		log.info("SMS to {}: visit booked for {}", owner.getTelephone(), visit.getDate());
	}

}

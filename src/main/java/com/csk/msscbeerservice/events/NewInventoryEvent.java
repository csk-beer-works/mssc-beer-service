package com.csk.msscbeerservice.events;

import com.csk.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

	public NewInventoryEvent(BeerDto beerDTO) {
		super(beerDTO);
		
	}

}

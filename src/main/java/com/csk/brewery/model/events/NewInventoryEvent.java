package com.csk.brewery.model.events;

import com.csk.brewery.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

	public NewInventoryEvent(BeerDto beerDTO) {
		super(beerDTO);
		
	}

}

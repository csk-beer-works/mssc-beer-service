package com.csk.msscbeerservice.events;

import com.csk.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

	public BrewBeerEvent(BeerDto beerDTO) {
		super(beerDTO);
		
	}

}

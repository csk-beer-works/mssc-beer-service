package com.csk.msscbeerservice.events;

import com.csk.brewery.model.events.BeerEvent;
import com.csk.brewery.model.BeerDto;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

	public BrewBeerEvent(BeerDto beerDTO) {
		super(beerDTO);
	}

}

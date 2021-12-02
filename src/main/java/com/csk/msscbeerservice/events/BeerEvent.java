package com.csk.msscbeerservice.events;

import java.io.Serializable;

import com.csk.msscbeerservice.web.model.BeerDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

	private static final long serialVersionUID = -5487107846192253630L;
	
	private final BeerDto beerDTO;

}

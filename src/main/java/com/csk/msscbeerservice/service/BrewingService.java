package com.csk.msscbeerservice.service;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.csk.msscbeerservice.domain.Beer;
import com.csk.msscbeerservice.events.BrewBeerEvent;
import com.csk.msscbeerservice.repository.BeerRepository;
import com.csk.msscbeerservice.service.inventory.BeerInventoryService;
import com.csk.msscbeerservice.web.mapper.BeerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
	
	private final BeerRepository beerRepository;
	private final BeerInventoryService beerInventoryService;
	private final JmsTemplate jmsTemplate;
	private final BeerMapper mapper;
	
	@Scheduled(fixedDelay = 5000) // every 5 seconds
	public void checkForLowInventory() {
		List<Beer> beers =  beerRepository.findAll();
		
		beers.forEach(beer -> {
			int beerQOH = beerInventoryService.getOnhandInventory(beer.getId());
			
			log.debug("Min OnHand is {}", beer.getMinOnHand());
			log.debug("Inventory is {}", beerQOH);
			
			if (beer.getMinOnHand() >= beerQOH) {
				jmsTemplate.convertAndSend("brewing-request", new BrewBeerEvent(mapper.beerToBeerDto(beer)));
			}
		});
	}

}

package com.csk.msscbeerservice.service.brewing;

import java.util.List;

import com.csk.msscbeerservice.config.JmsConfig;
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
	
	@Scheduled(fixedDelay = 60000) // every 5 seconds
	public void checkForLowInventory() {
		log.info("Fetching Beers from Repository........");
		List<Beer> beers =  beerRepository.findAll();
		
		beers.forEach(beer -> {
			int beerQOH = beerInventoryService.getOnhandInventory(beer.getId());
			
			log.info("Min OnHand is {}", beer.getMinOnHand());
			log.info("Inventory is {}", beerQOH);
			
			if (beer.getMinOnHand() >= beerQOH) {
				jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
						new BrewBeerEvent(mapper.beerToBeerDto(beer)));
			}
		});
	}

}

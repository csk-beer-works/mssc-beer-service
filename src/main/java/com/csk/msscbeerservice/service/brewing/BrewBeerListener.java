package com.csk.msscbeerservice.service.brewing;

import com.csk.msscbeerservice.config.JmsConfig;
import com.csk.msscbeerservice.domain.Beer;
import com.csk.msscbeerservice.events.BrewBeerEvent;
import com.csk.brewery.model.events.NewInventoryEvent;
import com.csk.msscbeerservice.repository.BeerRepository;
import com.csk.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void brewBeer(BrewBeerEvent event) {
        log.info("Brew Beer Event Trigerred....");
        BeerDto beerDto = event.getBeerDTO();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.info("Min on Hand:: {}, Quantity on Hand:: {}", beer.getMinOnHand(), beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}

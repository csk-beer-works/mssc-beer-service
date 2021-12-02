package com.csk.msscbeerservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.csk.msscbeerservice.domain.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID>{
	
	public Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, String beerStyle, PageRequest pageRequest);
	
	public Page<Beer> findAllByBeerStyle(String beerStyle, PageRequest pageRequest);

	public Optional<Beer> findByUpc(String upc);
	
	

}

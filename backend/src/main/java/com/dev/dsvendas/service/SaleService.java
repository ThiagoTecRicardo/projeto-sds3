package com.dev.dsvendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dsvendas.dto.SaleDTO;
import com.dev.dsvendas.entities.Sale;
import com.dev.dsvendas.repositories.SaleRepository;
import com.dev.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SellerRepository sellerRepository;

	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable) {
		
		sellerRepository.findAll();

		Page<Sale> ressult =  saleRepository.findAll(pageable);
		
		return ressult.map(x -> new SaleDTO(x));

	}

}
 	
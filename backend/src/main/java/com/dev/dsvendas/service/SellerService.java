package com.dev.dsvendas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dsvendas.dto.SellerDTO;
import com.dev.dsvendas.entities.Seller;
import com.dev.dsvendas.repositories.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	public List<SellerDTO> findAll() {

		List<Seller> ressult =  sellerRepository.findAll();
		
		return ressult.stream().map(x -> new SellerDTO(x)).collect(Collectors.toList());

	}

}
 	
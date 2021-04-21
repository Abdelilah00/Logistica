package com.logistica.services.Commands.Offer;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Offer;
import com.logistica.dtos.Commands.Offer.OfferCreateDto;
import com.logistica.dtos.Commands.Offer.OfferDto;
import com.logistica.dtos.Commands.Offer.OfferUpdateDto;
import org.springframework.stereotype.Service;

public interface IOfferService extends IBaseCrudService<Offer, OfferDto, OfferCreateDto, OfferUpdateDto> {

}

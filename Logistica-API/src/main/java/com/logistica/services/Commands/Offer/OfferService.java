package com.logistica.services.Commands.Offer;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Offer;
import com.logistica.dtos.Commands.Offer.OfferCreateDto;
import com.logistica.dtos.Commands.Offer.OfferDto;
import com.logistica.dtos.Commands.Offer.OfferUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class OfferService extends BaseCrudServiceImpl<Offer, OfferDto, OfferCreateDto, OfferUpdateDto> implements IOfferService {

    public OfferService() {
        super(Offer.class, OfferDto.class, OfferCreateDto.class, OfferUpdateDto.class);
    }
}

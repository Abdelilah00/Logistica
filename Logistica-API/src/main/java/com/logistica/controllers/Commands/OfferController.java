package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Offer;
import com.logistica.dtos.Commands.Offer.OfferCreateDto;
import com.logistica.dtos.Commands.Offer.OfferDto;
import com.logistica.dtos.Commands.Offer.OfferUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/offers")
public class OfferController extends BaseCrudController<Offer, OfferDto, OfferCreateDto, OfferUpdateDto> {
}

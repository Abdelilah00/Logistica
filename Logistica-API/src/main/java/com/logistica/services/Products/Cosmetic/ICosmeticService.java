package com.logistica.services.Products.Cosmetic;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Cosmetic;
import com.logistica.dtos.Products.Cosmetic.CosmeticCreateDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticUpdateDto;
import org.springframework.stereotype.Service;

public interface ICosmeticService extends IBaseCrudService<Cosmetic, CosmeticDto, CosmeticCreateDto, CosmeticUpdateDto> {

}

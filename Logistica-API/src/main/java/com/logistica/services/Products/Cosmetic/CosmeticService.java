package com.logistica.services.Products.Cosmetic;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Cosmetic;
import com.logistica.dtos.Products.Cosmetic.CosmeticCreateDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class CosmeticService extends BaseCrudServiceImpl<Cosmetic, CosmeticDto, CosmeticCreateDto, CosmeticUpdateDto> implements ICosmeticService {

    public CosmeticService() {
        super(Cosmetic.class, CosmeticDto.class, CosmeticCreateDto.class, CosmeticUpdateDto.class);
    }
}

package com.logistica.services.Products.Characteristic;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Characteristic;
import com.logistica.dtos.Products.Characteristic.CharacteristicCreateDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicService extends BaseCrudServiceImpl<Characteristic, CharacteristicDto, CharacteristicCreateDto, CharacteristicUpdateDto> implements ICharacteristicService {

    public CharacteristicService() {
        super(Characteristic.class, CharacteristicDto.class, CharacteristicCreateDto.class, CharacteristicUpdateDto.class);
    }
}

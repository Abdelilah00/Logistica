package com.logistica.services.Products.Characteristic;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Characteristic;
import com.logistica.dtos.Products.Characteristic.CharacteristicCreateDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicUpdateDto;

public interface ICharacteristicService extends IBaseCrudService<Characteristic, CharacteristicDto, CharacteristicCreateDto, CharacteristicUpdateDto> {

}

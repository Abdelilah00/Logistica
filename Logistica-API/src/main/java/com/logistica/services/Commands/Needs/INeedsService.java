package com.logistica.services.Commands.Needs;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Needs;
import com.logistica.dtos.Commands.Needs.NeedsCreateDto;
import com.logistica.dtos.Commands.Needs.NeedsDto;
import com.logistica.dtos.Commands.Needs.NeedsUpdateDto;

public interface INeedsService extends IBaseCrudService<Needs, NeedsDto, NeedsCreateDto, NeedsUpdateDto> {

}

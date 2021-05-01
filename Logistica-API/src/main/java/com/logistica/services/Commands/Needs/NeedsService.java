package com.logistica.services.Commands.Needs;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Needs;
import com.logistica.dtos.Commands.Needs.NeedsCreateDto;
import com.logistica.dtos.Commands.Needs.NeedsDto;
import com.logistica.dtos.Commands.Needs.NeedsUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class NeedsService extends BaseCrudServiceImpl<Needs, NeedsDto, NeedsCreateDto, NeedsUpdateDto> implements INeedsService {

    public NeedsService() {
        super(Needs.class, NeedsDto.class, NeedsCreateDto.class, NeedsUpdateDto.class);
    }
}

package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Needs;
import com.logistica.dtos.Commands.Needs.NeedsCreateDto;
import com.logistica.dtos.Commands.Needs.NeedsDto;
import com.logistica.dtos.Commands.Needs.NeedsUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/needss")
public class NeedsController extends BaseCrudController<Needs, NeedsDto, NeedsCreateDto, NeedsUpdateDto> {
}

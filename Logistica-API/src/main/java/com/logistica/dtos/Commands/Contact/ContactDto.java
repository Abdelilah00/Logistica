package com.logistica.dtos.Commands.Contact;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto extends BaseDto {
private String phone;
private String webSite;
private String email;
}

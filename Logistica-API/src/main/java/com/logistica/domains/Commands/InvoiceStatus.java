package com.logistica.domains.Commands;

import lombok.Getter;

@Getter
public enum InvoiceStatus {
    Publie,
    Partiel,
    Brouillon,
    Paid,
    Retarde,
}

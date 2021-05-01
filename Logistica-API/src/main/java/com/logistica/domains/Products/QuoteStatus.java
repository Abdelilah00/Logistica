package com.logistica.domains.Products;

import lombok.Getter;

@Getter
public enum QuoteStatus {
    Accepted,
    Rejected,
    InProcess,
    Canceled,
}

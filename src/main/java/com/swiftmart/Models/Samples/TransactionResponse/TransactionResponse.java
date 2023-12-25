package com.swiftmart.Models.Samples.TransactionResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse
{

    private TransactionData data;

}

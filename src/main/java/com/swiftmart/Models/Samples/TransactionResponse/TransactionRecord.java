package com.swiftmart.Models.Samples.TransactionResponse;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRecord
{

    private int id;
    private String description;
    private int amount;

}

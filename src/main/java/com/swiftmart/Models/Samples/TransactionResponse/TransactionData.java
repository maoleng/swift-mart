package com.swiftmart.Models.Samples.TransactionResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionData
{

    private List<TransactionRecord> records;

}

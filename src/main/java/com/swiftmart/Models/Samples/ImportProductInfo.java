package com.swiftmart.Models.Samples;

import com.swiftmart.Models.ImportProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImportProductInfo
{

    private Double importPrice;
    private int importedAmount;

}

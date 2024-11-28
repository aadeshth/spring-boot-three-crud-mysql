package com.rest.rest_example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse<T> {
    private String status;
    private String message;
    private String error;
    private List<T> errors;

    private T data;
    private List<T> dataList;

    /* {
  "status": 200,
  "message": "Recored Fetched!",
  "error": null,
  "data": {
    "id": 1,
    "name": "Mouse",
    "price": 1000
  },
  "dataList": null
}
     */
}

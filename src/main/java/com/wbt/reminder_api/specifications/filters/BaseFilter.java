package com.wbt.reminder_api.specifications.filters;

import lombok.Data;

@Data
public class BaseFilter {
    private int page = 0;
    private int size = 10;
}

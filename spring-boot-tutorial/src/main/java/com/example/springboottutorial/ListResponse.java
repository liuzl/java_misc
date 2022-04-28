package com.example.springboottutorial;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> {
    int total;
    int page;
    int size;
    List<T> data;
}

package com.example.demo.service;

import com.example.demo.model.ov.Result;

public interface LostFindService {
    Result lostFoundBoard();

    Result lostFoundMyboard(String userid);
}

package com.example.demo.controller;

import com.example.demo.utils.MapCache;

public  abstract class BaseController {

    protected MapCache cache = MapCache.single();
}

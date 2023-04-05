package com.denis.task2.service;

import com.denis.task2.util.FTPUtil;
import com.denis.task2.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FTPService {

    public List<Response> getInfo()  {
        return FTPUtil.getInfo();
    }
}

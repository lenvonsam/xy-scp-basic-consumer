package org.zhd.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zhd.consumer.service.dubbo.DptConsumerServiceImpl;

@Controller
class BaseController {
    Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    DptConsumerServiceImpl dptConsumerService;
}

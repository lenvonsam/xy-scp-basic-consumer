package org.zhd.consumer.service.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.basic.EmployeeDpi;
import org.xy.api.dpi.basic.OrgDpi;

@Service
public class EmpComsumerServiceImpl {
    private Logger log = LoggerFactory.getLogger(EmpComsumerServiceImpl.class);

    @Reference(version = "${api.service.version}", application = "${spring.application.name}",
            registry = "${dubbo.registry.address}", group = "${dubbo.group}")
    private EmployeeDpi employeeDpi;


}

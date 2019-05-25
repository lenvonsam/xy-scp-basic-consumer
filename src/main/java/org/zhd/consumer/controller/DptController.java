package org.zhd.consumer.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.DptDTO;
import org.xy.api.enums.ApiEnum;
import org.xy.api.utils.ApiUtil;
import org.zhd.consumer.entity.DptVO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("basicInfo")
public class DptController extends BaseController {
    @PostMapping("dpt")
    public Map<String, Object> saveDpt(DptDTO dptDto) throws Exception {
        dptConsumerService.saveDTO(dptDto);
        return ApiUtil.responseCode();
    }

    @GetMapping("dpt")
    public BaseListDTO<DptDTO> listDpt(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        return dptConsumerService.findByPage(currentPage, pageSize);
    }

    @GetMapping("dpt/{id}")
    public Map<String, Object> getDpt(@PathVariable("id") Long id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String message = "success";
        ApiEnum returnCode = ApiEnum.SUCCESS;

        DptVO res = dptConsumerService.findOne(id);
        map.put("obj", res);

        return ApiUtil.responseCode(map, returnCode, message);
    }

    @DeleteMapping("dpt/{id}")
    public Map<String, Object> deleteDpt(@PathVariable("id") Long id) throws Exception {
        String message = "success";
        ApiEnum returnCode = ApiEnum.SUCCESS;

        int res = dptConsumerService.delete(id);

        return ApiUtil.responseCode(null, returnCode, message);
    }

    @DeleteMapping("dpt")
    public Map<String, Object> batchDeleteDpt(HttpServletRequest request) throws Exception {
        String[] ids = request.getParameterValues("ids[]");
        String message = "success";
        ApiEnum returnCode = ApiEnum.SUCCESS;

        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        int res = dptConsumerService.batchDelete(idList);

        return ApiUtil.responseCode(null, returnCode, message);
    }

    @PutMapping("dpt")
    public Map<String, Object> updateDpt(DptVO dptVO) throws Exception {
        log.info(JSON.toJSONString(dptVO));
        log.info("测试中文乱码");
        String message = "success";
        ApiEnum returnCode = ApiEnum.SUCCESS;
        int res = dptConsumerService.saveDpt(dptVO, 1);
        if (res == -1) {
            returnCode = ApiEnum.FAILURE;
            message = "failure";
        }
        return ApiUtil.responseCode(null, returnCode, message);
    }
}

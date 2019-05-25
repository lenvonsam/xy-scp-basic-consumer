package org.zhd.consumer.service.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.basic.DptDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.DptDTO;
import org.zhd.consumer.entity.DptVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DptConsumerServiceImpl {
    private Logger log = LoggerFactory.getLogger(DptConsumerServiceImpl.class);

    @Reference(version = "${api.service.version}", application = "${spring.application.name}",
            registry = "${dubbo.registry.address}", group = "${dubbo.group}")
    private DptDpi dptDpi;

    public DptDTO saveDTO(DptDTO dpt) throws Exception {
        return dptDpi.save(dpt);
    }

    // flag 0 新增dpt 1 更新dpt
    public int saveDpt(DptVO dptVO, int flag) throws Exception {
        log.info(">>>测试中文乱码");
        DptDTO dptDTO = new DptDTO();
        dptDTO.setMemberCode("0000");
        dptDTO.setCode(dptVO.getDeptCode());
        dptDTO.setName(dptVO.getDeptName());
        dptDTO.setOrgCode(dptVO.getOrgCode());
        dptDTO.setRemark(dptVO.getDeptRemark());
        dptDTO.setManager(dptVO.getDeptManager());
        if (flag == 1) {
            dptDTO.setId(dptVO.getDeptId());
        }
        log.info(">>>" + JSON.toJSONString(dptDTO));
        DptDTO res = dptDpi.save(dptDTO);
        if (res == null) return -1;
        return 0;
    }

    public BaseListDTO<DptDTO> findByPage(Integer currentPage, Integer pageSize) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        return dptDpi.findByPg(params);
    }

    public int delete(Long id) throws Exception {
        return dptDpi.delete(id);
    }

    public int batchDelete(List<Long> ids) throws Exception {
        return dptDpi.batchDelete(ids);
    }

    public DptVO findOne(Long id) throws Exception {
        DptDTO dptDTO = dptDpi.findOne(id);
        if (dptDTO == null) {
            return null;
        }
        DptVO dptVO = new DptVO();
        dptVO.setDeptId(dptDTO.getId());
        dptVO.setDeptName(dptDTO.getName());
        dptVO.setDeptCode(dptDTO.getCode());
        dptVO.setMemberCode(dptDTO.getMemberCode());
        dptVO.setOrgCode(dptDTO.getOrgCode());
        dptVO.setDeptRemark(dptDTO.getRemark());
        dptVO.setDeptManager(dptDTO.getManager());
        return dptVO;
    }
}

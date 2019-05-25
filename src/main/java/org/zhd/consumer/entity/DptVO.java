package org.zhd.consumer.entity;

import lombok.Data;

@Data
public class DptVO {
    private Long deptId;
    // 数据共享
    private Integer basicShare;
    // 部门代码
    private String deptCode;
    // 叶子节点（0：部门组，1：部门明细）
    private Integer deptIsleaf;
    // 负责人
    private String deptManager;
    // 部门名称
    private String deptName;
    // 树节点代码
    private String deptNodecode;
    // 父代码
    private String deptParent;
    // 备注
    private String deptRemark;
    // 会员代码
    private String memberCode;
    // 所属机构
    private String orgCode;
}

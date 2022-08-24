package com.example.perms.bean.vo;

;
import com.example.perms.bean.dto.TreeNode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author linmr
 * @description:
 * @date 2020/12/16
 */
@Data
public class SysDeptTreeVO implements TreeNode<Long>, Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 父部门id
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 部门状态（0正常 1停用）
     */
    private String status;

    /**
     * 子类目列表
     */
    private List<SysDeptTreeVO> children;

    @Override
    public Long id() {
        return deptId;
    }

    @Override
    public Long parentId() {
        return parentId;
    }

    @Override
    public boolean root() {
        return this.parentId==0;
    }

    @Override
    public Integer getOrderSeq() {
        return orderNum;
    }

    @Override
    public void setChildren(List<? extends TreeNode<Long>> children) {
        this.children = (List<SysDeptTreeVO>) children;
    }

    @Override
    public List<? extends TreeNode<Long>> getChildren() {
        return this.children;
    }
}

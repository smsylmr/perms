package com.example.perms.bean.req;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Map;

/**
 * @author linmr
 * @description:
 * @date 2020/12/22
 */
@Data
public class BasePageRequest {
    /*
    当前页
     */
    private long curPage=1;
    /*
   显示条数
    */
    private long limit=10;
    /*
  排序字段
   */
    private String order;
    /*
 字段排序 desc，asc
  */
    private String orderBy;

}

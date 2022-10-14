package com.sample.mall.order.mapper;


import com.sample.mall.order.model.OrderItemDO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemMapper {

    /**
     * 新增订单明细
     *
     * @param list
     * @return
     */
    int insertOrderItems(List<OrderItemDO> list);
}

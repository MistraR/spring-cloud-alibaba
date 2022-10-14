package com.sample.mall.goods.service;


import com.sample.mall.common.dto.GoodsDTO;
import com.sample.mall.common.dto.OrderItemDTO;

import java.util.List;

/**
 * 商品Service
 */
public interface IGoodsService {

    /**
     * 新增商品
     *
     * @param goodsDTO
     * @return
     */
    boolean addGoods(GoodsDTO goodsDTO);

    /**
     * 获取商品
     *
     * @param id
     * @return
     */
    GoodsDTO getGoods(Long id);

    /**
     * 获取商品名称和价格
     *
     * @param id
     * @return
     */
    GoodsDTO getGoodsNameAndPrice(Long id);

    /**
     * 检查并扣减商品库存
     *
     * @param orderItemDTOList
     * @return
     */
    boolean decreaseStock(List<OrderItemDTO> orderItemDTOList);
}

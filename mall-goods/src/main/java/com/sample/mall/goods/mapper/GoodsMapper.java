package com.sample.mall.goods.mapper;


import com.sample.mall.common.dto.OrderItemDTO;
import com.sample.mall.goods.model.GoodsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品Mapper
 */
@Mapper
public interface GoodsMapper {

    /**
     * 新增商品
     *
     * @param goodsDO
     * @return
     */
    int insertGoods(GoodsDO goodsDO);

    /**
     * 查询商品
     *
     * @param id
     * @return
     */
    GoodsDO selectGoodsById(Long id);

    /**
     * 查询商品集合
     * @param ids
     * @return
     */
    List<GoodsDO> selectGoodsListByIds(List<Long> ids);

    /**
     * 批量更新商品库存
     * @param goodsDOList
     * @return
     */
    int updateGoodsListStock(List<GoodsDO> goodsDOList);
}

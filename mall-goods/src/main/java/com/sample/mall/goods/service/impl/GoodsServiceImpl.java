package com.sample.mall.goods.service.impl;


import com.sample.mall.common.annotation.MyCacheable;
import com.sample.mall.common.base.Constants;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.dto.GoodsDTO;
import com.sample.mall.common.dto.OrderItemDTO;
import com.sample.mall.common.exception.BusinessException;
import com.sample.mall.common.util.Assert;
import com.sample.mall.common.util.ObjectTransformer;
import com.sample.mall.goods.mapper.GoodsMapper;
import com.sample.mall.goods.model.GoodsDO;
import com.sample.mall.goods.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements IGoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    GoodsMapper goodsMapper;

    @Resource
    RedisTemplate<String, GoodsDO> redisTemplate;

    @Override
    public boolean addGoods(GoodsDTO goodsDTO) {
        GoodsDO goodsDO = ObjectTransformer.transform(goodsDTO, GoodsDO.class);
        int result = goodsMapper.insertGoods(goodsDO);
        return Assert.singleRowAffected(result);
    }

    @Override
    @MyCacheable(cacheName = Constants.GOODS_CACHE_KEY_PREFIX, key = "#id", expireInSeconds = 1, waitInSeconds = 2, bloomFilterName = "goodsBloomFilter")
    public GoodsDTO getGoods(Long id) {
        String cacheKey = String.format(Constants.GOODS_CACHE_KEY_PREFIX, id);
        GoodsDO goodsDO = redisTemplate.opsForValue().get(cacheKey);
        logger.info("key:{}, value:{}", cacheKey, goodsDO);
        if (goodsDO == null) {
            goodsDO = goodsMapper.selectGoodsById(id);
            Assert.notNull(goodsDO);
            redisTemplate.opsForValue().set(cacheKey, goodsDO);
        }
        return ObjectTransformer.transform(goodsDO, GoodsDTO.class);
    }

    /**
     * "mall:goods:%S::id"
     *
     * @param id
     * @return
     */
//    @CacheEvict
//    @CachePut
    @Cacheable(value = Constants.GOODS_CACHE_KEY_PREFIX, key = "#id")
    public GoodsDTO getGoods2(Long id) {
        String cacheKey = String.format(Constants.GOODS_CACHE_KEY_PREFIX, id);
        GoodsDO goodsDO = redisTemplate.opsForValue().get(cacheKey);
        logger.info("key:{}, value:{}", cacheKey, goodsDO);
        if (goodsDO == null) {
            goodsDO = goodsMapper.selectGoodsById(id);
            Assert.notNull(goodsDO);
            redisTemplate.opsForValue().set(cacheKey, goodsDO);
        }
        return ObjectTransformer.transform(goodsDO, GoodsDTO.class);
    }

    @Override
    public GoodsDTO getGoodsNameAndPrice(Long id) {
        GoodsDO goodsDO = null;
        try (Jedis jedis = new Jedis("localhost", 6379);) {

            String cacheKey = String.format(Constants.GOODS_CACHE_KEY_PREFIX, id);
            Map<String, String> cacheValue = jedis.hgetAll(cacheKey);
            logger.info("key:{}, value:{}", cacheKey, cacheValue);

            if (CollectionUtils.isEmpty(cacheValue)) {
                goodsDO = goodsMapper.selectGoodsById(id);
                Assert.notNull(goodsDO);

                cacheValue = new HashMap<>();
                cacheValue.put("name", goodsDO.getName());
                cacheValue.put("price", goodsDO.getPrice().toPlainString());
                jedis.hmset(cacheKey, cacheValue);
            } else {
                goodsDO = new GoodsDO();
                goodsDO.setName(cacheValue.get("name"));
                goodsDO.setPrice(new BigDecimal(cacheValue.get("price")));
            }
        }
        return ObjectTransformer.transform(goodsDO, GoodsDTO.class);
    }

    /**
     * 扣减商品库存
     *
     * <p>
     * update t_goods
     * set stock = stock - #{number}
     * where id = #{goodsId}
     * and stock >= #{number};
     * </p>
     * <p>
     * 重点：stock >= #{number} <br/>
     * 最后增加这个条件，既可以做到不需要提前锁库存，也可以起到防止超卖的作用
     *
     * @param orderItemDTOList
     * @return
     */
    @Override
    public boolean decreaseStock(List<OrderItemDTO> orderItemDTOList) {
        GoodsDO goodsDO;
        List<GoodsDO> goodsDOList = new ArrayList<>();
        for (OrderItemDTO orderItem : orderItemDTOList) {
            goodsDO = new GoodsDO();
            goodsDO.setId(orderItem.getGoodsId());
            // 这里使用stock字段，作为被扣减库存
            goodsDO.setStock(orderItem.getNumber());
            goodsDOList.add(goodsDO);
        }
        int result = goodsMapper.updateGoodsListStock(goodsDOList);
        if (result != goodsDOList.size()) {
            // 扣减成功的数量，与订单明细数量不一致，说明库存扣减有问题
            throw new BusinessException(ResponseEnum.GOODS_STOCK_NOT_ENOUGH);
        }
        return true;
    }

}

create table `t_order` (
  `id` bigint unsigned auto_increment comment '主键',
  `order_no` bigint unsigned not null comment '订单号',
  `amount` decimal(10, 2) not null comment '总金额',
  `status` tinyint unsigned default 0 comment '状态 0-初始化 1-提交 2-完成 3-撤销',
  `user_id` bigint unsigned not null comment '用户ID',
  `coupon_record_id` bigint unsigned default null comment '优惠券ID',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key pk_id (`id`),
  unique key uk_order_no(`order_no`),
  index idx_user_id(`user_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci comment='订单表';

create table `t_order_item` (
  `id` bigint unsigned auto_increment comment '主键',
  `order_no` bigint unsigned not null comment '订单号',
  `goods_id` bigint unsigned not null comment '商品ID',
  `number` int unsigned default 1 comment '数量',
  `amount` decimal(10, 2) default null comment '总金额',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key pk_id (`id`),
  index idx_order_no(`order_no`),
  index idx_goods_id(`goods_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci comment='订单条目表';
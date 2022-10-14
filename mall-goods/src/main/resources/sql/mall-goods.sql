create table `t_goods` (
  `id` bigint unsigned auto_increment comment '主键',
  `name` varchar(100) not null comment '商品名称',
  `description` varchar(1000) default '' comment '商品描述',
  `type` tinyint unsigned default 0 comment '商品类型 1-免费课 2-实战课 3-体系课',
  `price` decimal(10, 2) not null comment '商品价格',
  `stock` bigint default -1 comment '商品库存，-1代表没有库存限制',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update
                                 current_timestamp comment '更新时间',
  primary key pk_id (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci comment='商品表';
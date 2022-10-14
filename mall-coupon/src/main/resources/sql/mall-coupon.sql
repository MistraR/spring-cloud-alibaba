create table `t_coupon` (
    `id` bigint unsigned auto_increment comment '主键',
    `title` varchar(64) not null comment '优惠券标题',
    `with_amount` decimal(10, 2) not null default 0 comment '满减金额',
    `used_amount` decimal(10, 2) not null default 0 comment '优惠金额',
    `quota` bigint unsigned not null default 0 comment '发券数量',
    `take_count` bigint unsigned not null default 0 comment '已领取数量',
    `used_count` bigint unsigned not null default 0 comment '已使用数量',
    `status` tinyint unsigned not null default 1 comment '状态 1-生效 2-失效',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
    primary key pk_id (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci comment='优惠券表';


create table `t_coupon_record` (
   `id` bigint unsigned auto_increment comment '主键',
   `user_id` bigint unsigned not null comment '用户ID',
   `coupon_id` bigint unsigned not null comment '优惠券ID',
   `status` tinyint default 0 comment '状态 0-已领取未使用 1-已使用 -1为已过期',
   `create_time` datetime default current_timestamp comment '创建时间',
   `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
   primary key pk_id (`id`),
   index idx_user_id(`user_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci comment='优惠券记录表';
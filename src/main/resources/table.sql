CREATE TABLE `sale_system_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '订单ID',
  `commodity_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id',
  `selling_num` int(11) NOT NULL DEFAULT '0' COMMENT '售出数量',
  `selling_fee` int(11) NOT NULL DEFAULT '0' COMMENT '单个售价',
  `profit` int(11) NOT NULL DEFAULT '0' COMMENT '总盈利',
  `data_status` int(4) NOT NULL DEFAULT '0' COMMENT '数据状态',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `removed_at` timestamp NOT NULL DEFAULT '2001-01-01 12:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_order_id` (`order_id`),
  KEY `ix_commodity_id` (`commodity_id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`),
  KEY `ix_removed_at` (`removed_at`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='购物车'


CREATE TABLE `sale_system_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_phone` varchar(255) NOT NULL DEFAULT '' COMMENT '下单手机号',
  `user_nick` varchar(255) NOT NULL DEFAULT '' COMMENT '下单人微信昵称',
  `user_address` varchar(512) NOT NULL DEFAULT '' COMMENT '收货人地址',
  `delivery_num` int(11) NOT NULL DEFAULT '0' COMMENT '快递单号',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态 100 未收钱 200 已收钱未发货 300 已收钱已发货 400 被签收订单完成',
  `data_status` int(4) NOT NULL DEFAULT '0' COMMENT '数据状态',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `removed_at` timestamp NOT NULL DEFAULT '2001-01-01 12:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`),
  KEY `ix_removed_at` (`removed_at`)
) ENGINE=InnoDB AUTO_INCREMENT=524627 DEFAULT CHARSET=utf8 COMMENT='订单'

CREATE TABLE `sale_system_commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `sku` varchar(255) NOT NULL DEFAULT '' COMMENT '规格名',
  `purchase_fee` int(11) NOT NULL DEFAULT '0' COMMENT '采购价',
  `custom_fee` int(11) NOT NULL DEFAULT '0' COMMENT '定价',
  `selling_num` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `source` int(11) NOT NULL DEFAULT '0' COMMENT '货源',
  `data_status` int(4) NOT NULL DEFAULT '0' COMMENT '数据状态',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `removed_at` timestamp NOT NULL DEFAULT '2001-01-01 12:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`),
  KEY `ix_removed_at` (`removed_at`)
) ENGINE=InnoDB AUTO_INCREMENT=524627 DEFAULT CHARSET=utf8 COMMENT='商品信息'
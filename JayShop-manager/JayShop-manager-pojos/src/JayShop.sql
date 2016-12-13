use JayShop;



CREATE TABLE IF NOT EXISTS tb_content(
    id bigint(20) unsigned NOT  NULL AUTO_INCREMENT PRIMARY KEY,
    category_id bigint(20) unsigned NOT NULL COMMENT '内容条目ID',
    title varchar(200) DEFAULT  NULL COMMENT '内容标题',
    sub_title VARCHAR(200) DEFAULT NULL COMMENT '子标题',
    title_desc VARCHAR(200) DEFAULT NULL COMMENT '标题描述',
    url VARCHAR(200) DEFAULT NULL COMMENT '地址',
    pic VARCHAR(200) DEFAULT NULL COMMENT '图片地址',
    pic2 VARCHAR(200) DEFAULT NULL COMMENT '图片2',
    content TEXT DEFAULT NULL COMMENT '内容',
    created DATETIME DEFAULT NULL,
    updated DATETIME DEFAULT NULL,
    KEY(category_id),
    KEY (updated)
)ENGINE =Innodb DEFAULT CHAR SET =utf8;


CREATE TABLE IF NOT EXISTS  tb_content_category(
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT  '类目ID',
    parent_id BIGINT(20) UNSIGNED DEFAULT '0' COMMENT  '父类ID，ID为0的时候，表示顶级父类',
    name varchar(50) NOT NULL COMMENT '分类名称',
    status int(1) DEFAULT '1' COMMENT '类目状态（1，正常；2，删除',
    sort_order int(4) DEFAULT NULL COMMENT '排列序号,表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
    is_parent tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
    created DATETIME DEFAULT NULL,
    updated DATETIME DEFAULT NULL,
    KEY(parent_id,status) USING BTREE ,
    KEY(sort_order)
)ENGINE =Innodb DEFAULT CHARACTER SET =utf8;


CREATE TABLE IF NOT EXISTS tb_item(
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT  '商品ID，同时也是商品编号',
    title VARCHAR(100) NOT NULL COMMENT '商品标题',
    sell_point VARCHAR(500) DEFAULT NULL COMMENT '商品卖点',
    price BIGINT(20) NOT NULL COMMENT '商品价格',
    num INT(10) NOT NULL COMMENT '库存数量',
    barcode varchar(30) DEFAULT NULL COMMENT '商品条形码',
    image varchar(500) DEFAULT NULL COMMENT '商品图片',
    cid BIGINT(10) NOT NULL COMMENT '所属类目，叶子类目',
    status TINYINT(1) NOT NULL COMMENT '商品状态，1-正常，2-下架，3-删除',
    created DATETIME NOT NULL COMMENT '创建时间',
    updated DATETIME NOT NULL COMMENT '更新时间',
    key(cid),
    key(status),
    key(updated)
)ENGINE =Innodb default CHARACTER SET =utf8;

CREATE TABLE IF NOT EXISTS tb_item_cat(
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '类目ID',
    parent_id bigint(20) DEFAULT null comment '父类目ID=0时，代表的是一级的类目',
    name VARCHAR(50) DEFAULT NULL COMMENT '类目名称',
    status int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
    sort_order int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
    is_parent tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
    created datetime DEFAULT NULL COMMENT '创建时间',
    updated datetime DEFAULT NULL COMMENT '更新时间',
    KEY(parent_id,status) USING BTREE ,
    KEY (sort_order)
)ENGINE =innodb DEFAULT CHARACTER SET =utf8;

CREATE TABLE IF NOT EXISTS tb_item_desc(
    item_id BIGINT(20) NOT NULL PRIMARY KEY COMMENT '商品ID',
    item_desc text COMMENT '商品描述',
    created DATETIME DEFAULT NULL ,
    updated DATETIME DEFAULT NULL
)ENGINE =InnoDb DEFAULT CHARACTER SET =utf8 COMMENT='商品描述表';


CREATE TABLE IF NOT EXISTS tb_item_param (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    item_cat_id bigint(20) DEFAULT NULL COMMENT '商品类目ID',
    param_data text COMMENT '参数数据，格式为json格式',
    created datetime DEFAULT NULL,
    updated datetime DEFAULT NULL,
    PRIMARY KEY (id),
    KEY(item_cat_id)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品规则参数';



CREATE TABLE IF NOT EXISTS tb_item_param_item (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    item_id bigint(20) DEFAULT NULL COMMENT '商品ID',
    param_data text COMMENT '参数数据，格式为json格式',
    created datetime DEFAULT NULL,
    updated datetime DEFAULT NULL,
    PRIMARY KEY (id),
    KEY(item_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';


DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
    `order_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
    `payment` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
    `payment_type` int(2) DEFAULT NULL COMMENT '支付类型，1、在线支付，2、货到付款',
    `post_fee` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分',
    `status` int(10) DEFAULT NULL COMMENT '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
    `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
    `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
    `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
    `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
    `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
    `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
    `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `buyer_message` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
    `buyer_nick` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
    `buyer_rate` int(2) DEFAULT NULL COMMENT '买家是否已经评价',
    PRIMARY KEY (`order_id`),
    KEY `create_time` (`create_time`),
    KEY `buyer_nick` (`buyer_nick`),
    KEY `status` (`status`),
    KEY `payment_type` (`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
    `id` varchar(20) COLLATE utf8_bin NOT NULL,
    `item_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '商品id',
    `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
    `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
    `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
    `price` bigint(50) DEFAULT NULL COMMENT '商品单价',
    `total_fee` bigint(50) DEFAULT NULL COMMENT '商品总金额',
    `pic_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
    PRIMARY KEY (`id`),
    KEY `item_id` (`item_id`),
    KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tb_order_shipping`;
CREATE TABLE `tb_order_shipping` (
    `order_id` varchar(50) NOT NULL COMMENT '订单ID',
    `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货人全名',
    `receiver_phone` varchar(20) DEFAULT NULL COMMENT '固定电话',
    `receiver_mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
    `receiver_state` varchar(10) DEFAULT NULL COMMENT '省份',
    `receiver_city` varchar(10) DEFAULT NULL COMMENT '城市',
    `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
    `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
    `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码,如：310001',
    `created` datetime DEFAULT NULL,
    `updated` datetime DEFAULT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
    `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
    `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
    `created` datetime NOT NULL,
    `updated` datetime NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) USING BTREE,
    UNIQUE KEY `phone` (`phone`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户表';
CREATE TABLE admin(
    admin_id INT NOT NULL AUTO_INCREMENT  COMMENT '管理员id' ,
    admin_name VARCHAR(128)    COMMENT '管理员名称' ,
    admin_number VARCHAR(128)    COMMENT '管理员账号' ,
    admin_pwd VARCHAR(128)    COMMENT '管理员密码' ,
    role_id INT    COMMENT '角色id' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (admin_id)
) COMMENT = '管理员表 记录相关的管理员数据';;

ALTER TABLE admin COMMENT '管理员表';;
CREATE TABLE user(
    user_id INT NOT NULL AUTO_INCREMENT  COMMENT '用户id' ,
    openid VARCHAR(128)    COMMENT 'openid' ,
    id_card VARCHAR(128)    COMMENT '身份证' ,
    user_number VARCHAR(128)    COMMENT '用户账号学号' ,
    user_pwd VARCHAR(128)    COMMENT '用户密码' ,
    user_name VARCHAR(128)    COMMENT '用户名称' ,
    user_gender INT    COMMENT '用户性别' ,
    user_phone VARCHAR(128)    COMMENT '用户手机' ,
    user_address VARCHAR(128)    COMMENT '用户地址' ,
    user_credit INT   DEFAULT 100 COMMENT '信用值' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (user_id)
) COMMENT = '用户表 学生相关信息表';;

ALTER TABLE user COMMENT '用户表';;
CREATE TABLE goods(
    goods_id INT NOT NULL AUTO_INCREMENT  COMMENT '商品id' ,
    goods_name VARCHAR(128)    COMMENT '商品名称' ,
    goods_price DECIMAL(32,8)    COMMENT '商品价格' ,
    price_now DECIMAL(32,8)    COMMENT '当前价格' ,
    goods_info VARCHAR(512)    COMMENT '商品描述' ,
    state_on INT    COMMENT '是否上架' ,
    category_id INT    COMMENT '商品分类id' ,
    user_id INT    COMMENT '所属用户id' ,
    goods_img_id INT    COMMENT '商品照片' ,
    is_delete INT   DEFAULT 1 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    PRIMARY KEY (goods_id)
) COMMENT = '商品表 主要为商品的表';;

ALTER TABLE goods COMMENT '商品表';;
CREATE TABLE category(
    category_id INT NOT NULL AUTO_INCREMENT  COMMENT '分类id' ,
    category_name VARCHAR(128)    COMMENT '分类名称' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_BY VARCHAR(32)    COMMENT '创建人' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    PRIMARY KEY (category_id)
) COMMENT = '物品类型表 物品类型表';;

ALTER TABLE category COMMENT '物品类型表';;
CREATE TABLE auctions(
    auctions_id INT NOT NULL AUTO_INCREMENT  COMMENT '场次id' ,
    auctions_name VARCHAR(128)    COMMENT '场次名称' ,
    start DATETIME    COMMENT '开始时间' ,
    end DATETIME    COMMENT '结束时间' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (auctions_id)
) COMMENT = '竞拍场次表 ';;

ALTER TABLE auctions COMMENT '竞拍场次表';;
CREATE TABLE orders(
    orders_id INT NOT NULL AUTO_INCREMENT  COMMENT '订单id' ,
    serial_num VARCHAR(32)    COMMENT '流水号' ,
    goods_id INT    COMMENT '商品id' ,
    sell_users_id INT    COMMENT '竞拍商品拥有者id' ,
    buy_users_id INT    COMMENT '购买商品用户id' ,
    buy_price DECIMAL(32,8)    COMMENT '购买价格-成本价格' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (orders_id)
) COMMENT = '订单表 ';;

ALTER TABLE orders COMMENT '订单表';;
CREATE TABLE complaint(
    complaint_id INT NOT NULL AUTO_INCREMENT  COMMENT '投诉id' ,
    orders_id INT    COMMENT '订单id' ,
    user_id INT    COMMENT '申请人' ,
    remark VARCHAR(512)    COMMENT '备注-可以填写申诉结果' ,
    complaint_status INT    COMMENT '投诉状态' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (complaint_id)
) COMMENT = '投诉表 ';;

ALTER TABLE complaint COMMENT '投诉表';;
CREATE TABLE complaint_status(
    complaint_status_id INT NOT NULL AUTO_INCREMENT  COMMENT '投诉状态id' ,
    complaint_status_name VARCHAR(128)    COMMENT '投诉状态类型名称' ,
    PRIMARY KEY (complaint_status_id)
) COMMENT = '投诉状态类型表 ';;

ALTER TABLE complaint_status COMMENT '投诉状态类型表';;
CREATE TABLE community_show(
    show_id INT NOT NULL AUTO_INCREMENT  COMMENT '展示id' ,
    show_name VARCHAR(128)    COMMENT '展示名称' ,
    show_img_id INT    COMMENT '照片' ,
    is_delete INT   DEFAULT 0 COMMENT '状态' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    CREATED_BY INT    COMMENT '创建人' ,
    PRIMARY KEY (show_id)
) COMMENT = '工艺展示表 ';;

ALTER TABLE community_show COMMENT '工艺展示表';;
CREATE TABLE image(
    image_id INT NOT NULL AUTO_INCREMENT  COMMENT '图片id' ,
    image_url VARCHAR(512)    COMMENT '图片地址' ,
    CREATED_TIME DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    PRIMARY KEY (image_id)
) COMMENT = '图片表 ';;

ALTER TABLE image COMMENT '图片表';;

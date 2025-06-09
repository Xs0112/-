create table t_admin
(
    id          int auto_increment
        primary key,
    username    varchar(50)                         not null,
    password    varchar(100)                        not null,
    phone       varchar(20)                         not null,
    create_time timestamp default CURRENT_TIMESTAMP null,
    constraint username
        unique (username)
);

create table t_category
(
    category_id   int auto_increment comment '类目编号,自增'
        primary key,
    category_name varchar(100)      not null comment '类目名称',
    is_deleted    tinyint default 0 not null comment '是否删除，默认 0',
    constraint uk_category_name
        unique (category_name)
)
    row_format = DYNAMIC;

create table t_food
(
    food_id     int auto_increment comment '餐品编号，自增'
        primary key,
    food_name   varchar(50)       not null comment '餐品名称',
    category_id int               not null comment '所属类目',
    price       int               not null comment '单价',
    description varchar(100)      null comment '简介',
    is_deleted  tinyint default 0 not null comment '是否删除，默认 0',
    constraint t_food_ibfk_1
        foreign key (category_id) references t_category (category_id)
)
    row_format = DYNAMIC;

create table t_appraise
(
    id        int auto_increment
        primary key,
    appraise  text         null,
    food_name varchar(255) null,
    score     int          null,
    food_id   int          null,
    constraint t_appraise_ibfk_1
        foreign key (food_id) references t_food (food_id)
);

create index food_id
    on t_appraise (food_id);

create index category_id
    on t_food (category_id);

create table t_order
(
    order_id    int auto_increment comment '订单编号'
        primary key,
    desk_number int                                    not null comment '桌号',
    phone       varchar(20)                            not null comment '顾客手机号',
    food_id     int                                    not null comment '餐品编号',
    price       int                                    not null comment '单价',
    amount      int                                    not null comment '数量',
    total       int                                    not null comment '总价',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    state       varchar(10)  default '1'               null comment '状态(1 下单，2 接单，3 取消订单)默认 1',
    address     varchar(255) default ''                not null comment '客户地址',
    order_type  int          default 0                 not null comment '点餐类型'
)
    row_format = DYNAMIC;

create table t_shopowner
(
    shop_id   bigint auto_increment
        primary key,
    address   varchar(100) not null comment '地址',
    shopowner varchar(30)  null comment '店主',
    phone     varchar(20)  null comment '联系电话',
    password  varchar(100) null comment '登录密码'
)
    row_format = DYNAMIC;



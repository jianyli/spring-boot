drop table if exists t_user_info;
create table t_user_info
(
   id                   int not null,
   name                 varchar(11) not null unique,
   password             varchar(40) not null comment '密码',
   role                 varchar(12) comment '用户角色',
   nick_name            varchar(256) comment '昵称',
   birth_time           datetime,
   qq_number            varchar(12) comment 'qq号码',
   telephone            varchar(11) comment '电话号码',
   avatar_id            int comment '头像图片id',
   is_group             tinyint comment '是否含有分组',
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1 ;

alter table t_user_info comment '用户信息表';

drop table if exists t_group_info;

/*==============================================================*/
/* Table: t_group_info                                          */
/*==============================================================*/
create table t_group_info
(
   id                   int not null,
   user_id              int not null comment '创建者id',
   name                 varchar(256) comment '组名称',
   friend_number        int comment '该组别下的好友数',
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1;

alter table t_group_info comment '好友分组详细信息表';

drop table if exists t_user_friend;

/*==============================================================*/
/* Table: t_user_friend                                         */
/*==============================================================*/
create table t_user_friend
(
   id                   int not null,
   user_id              int not null comment '用户信息表id',
   friend_id            varchar(12) not null comment '用户角色',
   relationship         varbinary(256) comment '好友关系',
   nick_name            varchar(256) comment '好友昵称',
   group_id             int comment '好友组id',
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1;

alter table t_user_friend comment '用户好友表';

drop table if exists t_blog_info;

/*==============================================================*/
/* Table: t_blog_info                                           */
/*==============================================================*/
create table t_blog_info
(
   id                   int not null,
   user_id              int not null,
   blog_title           varchar(256) not null comment '博客标题',
   blog_address         varchar(256) not null comment '保存地址',
   view_number          int comment '博客浏览人数',
   star_number          int comment '点赞数',
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1;

drop table if exists t_picture_info;

/*==============================================================*/
/* Table: t_picture_info                                        */
/*==============================================================*/
create table t_picture_info
(
   id                   int not null,
   business_id          int not null,
   picture_type         varchar(256) NOT NULL COMMENT '图片类型',
   address              varchar(256) not null comment '图片保存地址',
   size                 int comment '图片大小',
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1;

alter table t_picture_info comment '图片详细表';

drop table if exists t_blog_picture;

/*==============================================================*/
/* Table: t_blog_picture                                        */
/*==============================================================*/
create table t_blog_picture
(
   id                   int not null,
   blog_id              int not null,
   picture_id           int not null comment '图片id',
   address              varchar(256) not null comment '博客图片保存地址',
   number               int,
   create_time          datetime,
   been_delete          tinyint comment '数据是否被删，否:0，是:1',
   delete_time          datetime,
   deleter_id           int,
   last_change_time     datetime comment '上次更改时间',
   primary key (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 auto_increment = 1;

alter table t_blog_picture comment '博客图片信息';

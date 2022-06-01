drop table if exists accommodation;
drop table if exists accommodation_option_line;
drop table if exists amenity_category;
drop table if exists amenity_sub_category;
drop table if exists city;
drop table if exists comment;
drop table if exists image;
drop table if exists member;
drop table if exists reservation;
drop table if exists wish;
drop table if exists tag;
drop table if exists accommodation_tag;

create table city
(
    city_id   bigint auto_increment primary key,
    image     varchar(500) null,
    name      varchar(20) null,
    nation    varchar(20) null,
    latitude  double null,
    longitude double null
);

create table member
(
    member_id     bigint auto_increment primary key,
    gender        varchar(10) null,
    github_id     varchar(20) null,
    name          varchar(10) null,
    profile_image varchar(500) null,
    role          varchar(10) null
);

create table accommodation
(
    accommodation_id     bigint not null primary key,
    accommodation_type   varchar(25) null,
    gu                   varchar(20) null,
    home_address         varchar(120) null,
    street               varchar(30) null,
    zip_code             varchar(30) null,
    average_rating       double not null,
    description          varchar(1000) null,
    point                geometry null,
    max_number_of_people int    not null,
    name                 varchar(30) null,
    price                decimal(19, 2) null,
    bath_rooms           int    not null,
    bed_rooms            int    not null,
    beds                 int    not null,
    city_id              bigint null,
    member_id            bigint null,
    constraint FKcuegd0ulld57ijnms8yymxffq foreign key (member_id) references member (member_id),
    constraint FKe54ohchibbinwp7qtvl5vmvlo foreign key (city_id) references city (city_id)
);

create table accommodation_option_line
(
    accommodation_option_line_id bigint auto_increment primary key,
    name                         varchar(50) null,
    price                        decimal(19, 2) null,
    accomodation_id              bigint null,
    constraint FKlaldc0eg339j4w1kk2gab0crb foreign key (accomodation_id) references accommodation (accommodation_id)
);

create table amenity_category
(
    amenity_category_id bigint auto_increment primary key,
    name                varchar(20) null,
    accommodation_id    bigint null,
    constraint FK1u7ueiyctcp5xl8mnxjm1aljp foreign key (accommodation_id) references accommodation (accommodation_id)
);

create table amenity_sub_category
(
    amenity_subcategory_id bigint auto_increment primary key,
    description            varchar(30) null,
    name                   varchar(20) null,
    amenity_category_id    bigint null,
    constraint FKnmqt7ibb6v803663qwy9vk794 foreign key (amenity_category_id) references amenity_category (amenity_category_id)
);

create table comment
(
    comment_id       bigint auto_increment primary key,
    content          varchar(255) null,
    create_at        datetime null,
    last_modified_at datetime null,
    rating           double not null,
    accommodation_id bigint null,
    member_id        bigint null,
    constraint FKmrrrpi513ssu63i2783jyiv9m foreign key (member_id) references member (member_id),
    constraint FKsiomq1kwird37rcpi3pufwl86 foreign key (accommodation_id) references accommodation (accommodation_id)
);

create table image
(
    image_id         bigint auto_increment primary key,
    file_name        varchar(1000) null,
    image_url        varchar(1000) null,
    accommodation_id bigint null,
    constraint FKg5abixlfdp3f6fn9ag5kqigjo foreign key (accommodation_id) references accommodation (accommodation_id)
);

create table reservation
(
    reservation_id     int auto_increment primary key,
    adults             int not null,
    children           int not null,
    infants            int not null,
    reservation_status varchar(20) null,
    checkin_time       datetime null,
    checkout_time      datetime null,
    total_day          int not null,
    total_people       int not null,
    total_price        decimal(19, 2) null,
    accommodation_id   bigint null,
    member_id          bigint null,
    constraint FK68999qe28ym9eqqlowybh9nvn foreign key (member_id) references member (member_id),
    constraint FKoa1arn2i6kipar0jakkwtsjip foreign key (accommodation_id) references accommodation (accommodation_id)
);

create table tag
(
    tag_id bigint auto_increment primary key,
    name   varchar(20) null
);

create table accommodation_tag
(
    accommodation_tag_id bigint auto_increment primary key,
    accommodation_id     bigint null,
    tag_id               bigint null,
    constraint FKdj33gibbky10hd83odgyhgsnx foreign key (tag_id) references tag (tag_id),
    constraint FKir1bmhty3lvv2xbwepd42hues foreign key (accommodation_id) references accommodation (accommodation_id)
);

create index accommodation_tag_index on accommodation_tag (accommodation_tag_id, accommodation_id, tag_id);

create index tag_index on tag (tag_id, name);

create table wish
(
    wish_id          bigint auto_increment primary key,
    accommodation_id bigint null,
    member_id        bigint null,
    constraint FK6u8987fptq8d75x7ix2pnhmhy foreign key (accommodation_id) references accommodation (accommodation_id),
    constraint FK70nrc4a6uvljrtemsn80eq1gd foreign key (member_id) references member (member_id)
);



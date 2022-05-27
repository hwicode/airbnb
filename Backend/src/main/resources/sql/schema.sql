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

create table accommodation
(
    accommodation_id     integer          not null auto_increment,
    accommodation_type   varchar(255),
    gu                   varchar(255),
    home_address         varchar(255),
    street               varchar(255),
    zip_code             varchar(255),
    average_rating       double precision not null,
    description          varchar(255),
    latitude             double precision not null,
    longtitude           double precision not null,
    max_number_of_people integer          not null,
    name                 varchar(255),
    price                decimal(19, 2),
    bath_rooms           integer          not null,
    bed_rooms            integer          not null,
    beds                 integer          not null,
    city_id              integer,
    member_id            integer,
    primary key (accommodation_id)
) engine = InnoDB;

create table amenity_category
(
    amenity_category_id bigint not null auto_increment,
    name                varchar(255),
    accommodation_id    integer,
    primary key (amenity_category_id)
) engine = InnoDB;

create table amenity_sub_category
(
    amenity_subcategory_id bigint not null auto_increment,
    description            varchar(255),
    name                   varchar(255),
    amenity_category_id    bigint,
    primary key (amenity_subcategory_id)
) engine = InnoDB;

create table city
(
    city_id integer not null auto_increment,
    image   varchar(255),
    name    varchar(255),
    nation  varchar(255),
    primary key (city_id)
) engine = InnoDB;

create table comment
(
    comment_id       integer          not null auto_increment,
    content          varchar(255),
    create_at        datetime(6),
    last_modified_at datetime(6),
    rating           double precision not null,
    accommodation_id integer,
    member_id        integer,
    primary key (comment_id)
) engine = InnoDB;

create table image
(
    image_id         integer not null auto_increment,
    file_name        varchar(255),
    image_url        varchar(255),
    accommodation_id integer,
    primary key (image_id)
) engine = InnoDB;

create table member
(
    member_id     integer not null auto_increment,
    bio           varchar(255),
    github_id     varchar(255),
    name          varchar(255),
    profile_image varchar(255),
    role          varchar(255),
    primary key (member_id)
) engine = InnoDB;

create table reservation
(
    reservation_id   integer not null auto_increment,
    adults           integer not null,
    children         integer not null,
    infants          integer not null,
    checkin_time     datetime(6),
    checkout_time    datetime(6),
    total_day        integer not null,
    total_people     integer not null,
    total_price      decimal(19, 2),
    accommodation_id integer,
    member_id        integer,
    primary key (reservation_id)
) engine = InnoDB;

create table wish
(
    wish_id          integer not null auto_increment,
    accommodation_id integer,
    member_id        integer,
    primary key (wish_id)
) engine = InnoDB;

create table accommodation_option_line
(
    accommodation_option_line_id bigint not null auto_increment,
    name                         varchar(255),
    price                        decimal(19, 2),
    accomodation_id              integer,
    primary key (accommodation_option_line_id)
) engine = InnoDB;

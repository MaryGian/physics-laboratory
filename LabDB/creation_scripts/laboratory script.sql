-- drop database laboratory;
create database if not exists laboratory;
use laboratory;

create table if not exists institutes(
	institute_id int primary key auto_increment,
	institute_name varchar(50)
);
ALTER TABLE institutes ADD constraint uniqueInstitute unique(institute_name);


create table if not exists research_details (
	research_id int primary key auto_increment,
    program_name varchar(100),
    ongoing boolean not null default false

);

create table if not exists took_place(
	research_id int,
    institute_id int,
    constraint rdetails_tplace foreign key(research_id) references research_details(research_id) on delete cascade on update cascade,
    constraint institutes_tplace foreign key(institute_id) references institutes(institute_id) on delete cascade on update cascade,
    constraint unique_tplace unique(research_id,institute_id)
);

create table if not exists roles(
	role_id int primary key auto_increment,
	role_name varchar(30) unique
);

create table if not exists pub_category(
	category_id int primary key auto_increment,
    category_name varchar(70) unique
);



create table if not exists publications(
	pub_id int primary key auto_increment,
	category_id int,
	pub_title varchar(150),
    date_of_pub date,
	publisher varchar(150),
    publish_at_conference boolean default false,
    co_author_foreign boolean default false,
    constraint category_pub foreign key (category_id) references pub_category(category_id) on update cascade
);

create table if not exists co_authors(
	cauthor_id int primary key auto_increment,
    cauthor_name varchar(50)
);

create table if not exists co_write(
	pub_id int,
	cauthor_id int,
	constraint pub_cwrite foreign key (pub_id) references publications(pub_id) on delete cascade on update cascade,
    constraint cauthor_cwrite foreign key (cauthor_id) references co_authors(cauthor_id) on delete cascade on update cascade,
	constraint unique_pub_cauth unique(pub_id,cauthor_id)
);


create table if not exists lab_members(
		member_id int primary key auto_increment,
        role_id int default 7,
        member_name varchar(30),
        sir_name varchar(30),
        email varchar(40) unique,
        short_cv varchar(500),
        web_page varchar(400),
        constraint roles_members foreign key (role_id) references roles(role_id) on update cascade
        
);



create table publish(
	pub_id int not null,
    member_id int not null,
    constraint pub_publish foreign key (pub_id) references publications(pub_id) on delete cascade on update cascade,
    constraint members_publish foreign key(member_id) references lab_members(member_id) on delete cascade on update cascade,
	constraint unique_publish unique(pub_id, member_id)
);

create table research(
	member_id int not null,
    research_id int not null,
    constraint members_research foreign key(member_id) references lab_members(member_id) on delete cascade on update cascade,
    constraint research_details_research foreign key(research_id) references research_details(research_id) on delete cascade on update cascade,
    constraint unique_research unique(member_id,research_id)
    );

create table if not exists courses(
	course_id int primary key auto_increment,
    course_name varchar(60),
    ects int,
    undergraduate boolean default false,
    constraint unique_course unique(course_name,undergraduate)
);


create table if not exists teach(
	member_id int,
    course_id int,
    hours_for_each int,
    constraint members_teach foreign key (member_id)references lab_members(member_id) on delete cascade on update cascade,
    constraint courses_teach foreign key (course_id)references courses(course_id) on delete cascade on update cascade,
    primary key (course_id,member_id)
    );

create table if not exists announcements(
	an_id int auto_increment primary key,
    member_id int,
    course_id int,
    title varchar(100),
    detail varchar(300),
    constraint cources_announc foreign key (course_id) references courses(course_id) on delete cascade on update cascade,
    constraint members_announc foreign key (member_id) references lab_members(member_id) on delete cascade on update cascade
);
alter table announcements
add column date_of_announcement date;

show tables;
select * from roles;

show create table research;
drop table research;


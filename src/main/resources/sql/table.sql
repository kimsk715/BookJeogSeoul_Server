/* 관리자 정보 0421 update */
/* id,pw, 이름, 부서명, 재직상태, 직급 */
create table tbl_admin(
    id bigint primary key,
    admin_id varchar(255) unique,
    admin_password varchar(255) unique,
    admin_name varchar(255) not null,
    admin_department varchar(255),
    admin_status varchar(255) default 'active',
    admin_grade varchar(255),
    constraint fk_admin_member foreign key(id)
    references tbl_member(id),
    createdDate datetime default current_timestamp,
    updatedDate datetime default current_timestamp
);

/* tbl_normal_book_post 테이블 */
/* book_id 컬럼 --> book_isbn 이름 수정하기. */
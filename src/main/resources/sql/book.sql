insert into tbl_selected_book_temp (book_isbn)
values (${bookisbn});


select * from tbl_selected_book_temp;


alter table tbl_book_donate
    add book_title varchar(255) not null;


select  member_id, book_isbn, count(*) as count
from tbl_book_post_like bpl join tbl_book_post bp
on bpl.book_post_id = bp.id
group by book_isbn
order by count desc
limit 10



create table tbl_past_book_post(
                                   id bigint auto_increment primary key,
                                   book_post_id bigint not null,
                                   book_post_title varchar(255),
                                   book_post_text varchar(255),
                                   createdDate datetime default current_timestamp,
                                   updatedDate datetime default current_timestamp,
                                   constraint fk_past_post_book_post foreign key(book_post_id)
                                       references tbl_book_post(id)
);

/* 이 달의 독후감 후보들을 모아놓은 테이블 */
create table tbl_monthly_book_post(
                                      id bigint auto_increment primary key,
                                      book_post_id bigint not null,
                                      book_post_title varchar(255),
                                      book_post_text varchar(255),
                                      createdDate datetime default current_timestamp,
                                      updatedDate datetime default current_timestamp,
                                      constraint fk_monthly_book_book_post foreign key(book_post_id)
                                          references tbl_book_post(id)
);

/* 매 달 1등 독후감 저장하는 테이블 */
create table tbl_best_book_post(
                                   id bigint auto_increment primary key,
                                   book_post_id bigint not null,
                                   book_post_title varchar(255),
                                   book_post_text varchar(255),
                                   createdDate datetime default current_timestamp,
                                   updatedDate datetime default current_timestamp,
                                   constraint fk_best_post_book_post foreign key(book_post_id)
                                       references tbl_book_post(id)
);




SELECT member_name, member_nickname, COUNT(p.id) AS post_count
FROM tbl_personal_member m
          JOIN tbl_post p
              ON m.id = p.member_id
where post_type = 'BOOK_POST'
GROUP BY m.id
ORDER BY post_count DESC;



select distinct member_nickname, count(p.id) as post_count
FROM tbl_personal_member m
         JOIN tbl_post p
              ON m.id = p.member_id
         join tbl_member_profile mp
              on p.member_id = mp.member_id
         join tbl_file f
              on mp.id = f.id

where post_type = 'BOOK_POST'
group by m.id
ORDER BY post_count DESC
limit 4;



select *, count(p.id) as post_count
FROM tbl_personal_member m
         JOIN tbl_post p
              ON m.id = p.member_id
         join (
    select f.id as ID, mp.member_id as MEMBERID
    from tbl_file f
             join tbl_member_profile mp
                  on mp.id = f.id) file on m.id = file.MEMBERID
where post_type = 'BOOK_POST'
group by m.id
ORDER BY post_count DESC
limit 4;


select *
from tbl_personal_member
where member_email = 'rksel0712@gmail.com'

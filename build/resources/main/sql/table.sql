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

desc tbl_comment;
member_name, file_path, file_name, count(p.id) as post_count

ORDER BY post_count DESC

GROUP BY p.id


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



select member_nickname, file_path, file_name, count(p.id) as post_count
FROM tbl_personal_member m
         JOIN tbl_post p
              ON m.id = p.member_id
         join tbl_member_profile mp
              on m.id = mp.member_id
         join tbl_file f
              on mp.id = f.id
where post_type = 'BOOK_POST'
group by m.id
ORDER BY post_count DESC
limit 4



select member_nickname, count(p.id) as post_count
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

# 멘션당한 사람 id 조회
select c.id, mentioned_member_id
from tbl_comment_mention cm join tbl_comment c
on cm.comment_id = c.id
where c.id = ${commentId};

# 멘션한 사람 id 조회
select c.id, member_id
from tbl_comment_mention cm join tbl_comment c
                                 on cm.comment_id = c.id
where c.id = ${commentId};

# 멘션된 댓글이 있는 게시글 id 조회
select c.id, post_id
from tbl_comment_mention cm join tbl_comment c
                                 on cm.comment_id = c.id
where c.id = ${commentId};


select *
from tbl_follow;

select bp.id as book_post_id, bp.book_post_title, bp.book_post_text, pm.member_name, pm.member_nickname, bp.book_isbn, p.member_id, f.file_name, f.file_path, p.createdDate,
       (select count(*)
        from tbl_book_post_like l
        where l.book_post_id = bp.id) as like_count
from tbl_book_post bp
         join tbl_post p on p.id = bp.id
         join tbl_member m on p.member_id = m.id
         join tbl_personal_member pm on pm.id = m.id
         join tbl_member_profile mp on mp.member_id = m.id
         left join tbl_file f on f.id = mp.id
where bp.book_post_is_public = 'PUBLIC'
order by p.createdDate desc
limit 12 offset 0;

select
    bp.id as book_post_id,
    bp.book_post_title,
    bp.book_post_is_public,
    p.id as post_id,
    p.member_id,
    m.id as member_id,
    pm.member_name,
    pm.member_nickname
from tbl_book_post bp
         join tbl_post p on p.id = bp.id
         join tbl_member m on p.member_id = m.id
         join tbl_personal_member pm on pm.id = m.id
where bp.book_post_is_public = 'PUBLIC';

select mbp.id, mbp.book_post_id, date_format(mbp.createdDate, '%y%m') as createdDate from tbl_event e
join tbl_monthly_book_post mbp on date_format(mbp.createdDate, '%y%m') = concat(e.year,e.month)
select pm.id, member_email, member_name, member_phone, member_mileage, member_status,
       date(createdDate) as createdDate, date(updatedDate) as updatedDate
from tbl_personal_member pm
         join tbl_member m
              on pm.id = m.id
where member_name like '%트10%';
select id, member_email, member_password, member_name, member_mileage, member_status, member_birth, member_gender, member_phone, member_nickname
from tbl_personal_member
where member_email = 'rksel0712@gmaul.com' and member_name = 1234 and member_birth = 123412 and member_gender = 1;



select id, member_email, member_password, member_name, member_mileage, member_status, member_birth, member_gender, member_phone, member_nickname
from tbl_personal_member
where member_email = 'huh0830@naver.com';









/* 독후감을 쓴 member id 를 4개 뽑아온다, <- 목표 */
select p.member_id, member_nickname, file_path, file_name, count(p.id) as post_count
FROM tbl_personal_member m
         JOIN tbl_post p
              ON m.id = p.member_id
         join tbl_member_profile mp
              on m.id = mp.member_id
         join tbl_file f
              on mp.id = f.id
where post_type = 'BOOK_POST'
group by p.member_id, member_nickname, file_path, file_name
ORDER BY post_count DESC
limit 4
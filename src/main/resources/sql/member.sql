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

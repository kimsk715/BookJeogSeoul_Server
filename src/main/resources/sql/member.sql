select pm.id, member_email, member_name, member_phone, member_mileage, member_status,
       date(createdDate) as createdDate, date(updatedDate) as updatedDate
from tbl_personal_member pm
         join tbl_member m
              on pm.id = m.id
where member_name like '%트10%';

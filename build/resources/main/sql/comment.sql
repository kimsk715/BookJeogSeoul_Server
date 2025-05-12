SELECT
    ca.*,
    c.*,
    m.*,
    pm.member_name AS personal_name,
    sp.sponsor_name AS sponsor_name
FROM tbl_comment_alarm ca
         JOIN tbl_comment c ON ca.comment_id = c.id
         JOIN tbl_member m ON c.member_id = m.id
         LEFT JOIN tbl_personal_member pm ON m.id = pm.id AND m.member_type = 'PERSONAL'
         LEFT JOIN tbl_sponsor_member sp ON m.id = sp.id AND m.member_type = 'SPONSOR'
WHERE ca.receiver_id = #{receiverId}


select *
from tbl_comment c join tbl_member m
on c.member_id = m.id
join tbl_post p
on c.post_id = p.id



alter table tbl_book_donate
    alter column book_received_status set default '수취대기';
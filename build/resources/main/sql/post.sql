select count(*)
from tbl_member_inquiry
    where
    member_inquiry_title like '%트1%' or
    member_inquiry_text like '%트1%' or
    member_inquiry_answer like '%트1%';


select date(createdDate) as createdDate
from tbl_post;



select dc.id, donate_cert_title, donate_cert_text, post_type, member_id, datediff(curdate(), p.createdDate) as createdDate, p.updatedDate
from tbl_donate_cert dc
         join tbl_post p on dc.id = p.id
order by dc.id desc


SELECT
    p.id,
    p.donate_cert_title,
    DATEDIFF(CURDATE(), p.createdDate) AS createdDate
FROM Post p;
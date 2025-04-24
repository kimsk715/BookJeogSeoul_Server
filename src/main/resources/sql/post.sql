select count(*)
from tbl_member_inquiry
    where
    member_inquiry_title like '%트1%' or
    member_inquiry_text like '%트1%' or
    member_inquiry_answer like '%트1%';


select date(createdDate) as createdDate
from tbl_post;
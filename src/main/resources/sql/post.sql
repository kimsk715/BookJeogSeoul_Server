select bp.id, bp.book_post_title, BP.book_post_text, bp.book_post_is_public, bp.book_post_start_date, bp.book_post_end_date,
nbp.book_id, sbp.book_id, sbp.book_post_status
from tbl_book_post bp
left join tbl_normal_book_post nbp
on bp.id = nbp.id
left join tbl_selected_book_post sbp
    on bp.id = sbp.id
;


select *


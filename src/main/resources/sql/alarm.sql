select
    bp.id as book_post_id,
    bp.book_title,
    bp.book_isbn,
    count(bpl.id) as like_count,
    pm.member_name,
    pm.id as member_id,
    f.file_path,
    f.file_name,
    bp.book_post_title,
    bp.book_post_text,
    bp.book_post_start_date,
    bp.book_post_end_date,
    p.createdDate
from tbl_book_post bp
         join tbl_post p on bp.id = p.id
         join tbl_personal_member pm on pm.id = p.member_id
         left join tbl_member_profile mp on mp.member_id = pm.id
         left join tbl_file f on f.id = mp.id
         left join tbl_book_post_like bpl on bpl.book_post_id = bp.id
where bp.book_post_is_public = 'PUBLIC'
  and bp.id = 30
group by
    bp.id,
    bp.book_title,
    bp.book_isbn,
    pm.member_name,
    f.file_path,
    f.file_name,
    f.file_text,
    bp.book_post_start_date,
    bp.book_post_end_date,
    p.createdDate
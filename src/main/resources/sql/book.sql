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
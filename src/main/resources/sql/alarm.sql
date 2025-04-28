SELECT  id, member_id, book_isbn, count(*) as count
FROM tbl_member_history
GROUP BY book_isbn
order by count desc
    LIMIT 10;
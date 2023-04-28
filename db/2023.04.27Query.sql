SELECT 
	bt.book_id,
    bt.book_name,
    bt.author_id,
    bt.publisher_id,
    bt.category_id,
    bt.cover_img_url,
    
    at.author_id,
    at.author_name,
    
    pt.publisher_id,
    pt.publisher_name,
    
    ct.category_id,
    ct.category_name
FROM 
	book_tb bt
    left outer join author_tb at on(at.author_id = bt.author_id)
    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)
    left outer join category_tb ct on(ct.category_id = bt.category_id)
order by
	bt.book_id;
    
SELECT 
	count(*)
FROM 
	book_tb bt
    left outer join author_tb at on(at.author_id = bt.author_id)
    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)
    left outer join category_tb ct on(ct.category_id = bt.category_id)
where
	bt.book_name like '%%';
    
select 
	count(*)
from
	book_tb bt
	left outer join author_tb at on(at.author_id = bt.author_id)
	left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)
	left outer join category_tb ct on(ct.category_id = bt.category_id)
where
	bt.book_id < 60
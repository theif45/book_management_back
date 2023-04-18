select
	0,
    cover_img_url
from
	book_list_tb
group by
	cover_img_url;
    
insert into author_tb
select
	0,
    author_name
from
	book_list_tb
group by
	author_name;

insert into book_tb 
SELECT
	0 as book_id,
	book_name,
    author_id,
    publisher_id,
    category_id
from
	book_list_tb bl
    left outer join author_tb a on(a.author_name = bl.author_name)
    left outer join publisher_tb ps on(ps.publisher_name = bl.publisher_name)
    left outer join category_tb cg on(cg.category_name = bl.category_name)
group by
	book_id,
	book_name,
    author_id,
    publisher_id,
    category_id;

insert into book_tb
SELECT
	0 as book_id,
	book_name,
	author_id,
	publisher_id,
	category_id,
	cover_img_url
from
	(SELECT
		row_number() over(partition by
			book_name,
			author_id,
			publisher_id,
			category_id order by book_name) as num,
		book_name,
		author_id,
		publisher_id,
		category_id,
		cover_img_url
	from
		book_list_tb bl
		left outer join author_tb a on(a.author_name = bl.author_name)
		left outer join publisher_tb ps on(ps.publisher_name = bl.publisher_name)
		left outer join category_tb cg on(cg.category_name = bl.category_name)
		) book_list
where
	num = 1;
    
select
	*
from
	book_list_tb bl
    left outer join author_tb a on(a.author_name = bl.author_name)
    left outer join publisher_tb ps on(ps.publisher_name = bl.publisher_name)
    left outer join category_tb cg on(cg.category_name = bl.category_name)
    left outer join book_tb b on(
			b.book_name = bl.book_name
		and	b.author_id = a.author_id
        and b.publisher_id = ps.publisher_id
        and b.category_id = cg.category_id);


update
	book_list_tb bl
    left outer join author_tb a on(a.author_name = bl.author_name)
    left outer join publisher_tb ps on(ps.publisher_name = bl.publisher_name)
    left outer join category_tb cg on(cg.category_name = bl.category_name)
    left outer join book_tb b on(
			b.book_name = bl.book_name
		and	b.author_id = a.author_id
        and b.publisher_id = ps.publisher_id
        and b.category_id = cg.category_id)
set
	bl.book_id = b.book_id;

update book_list_tb
set registe_date = DATE_ADD(
	DATE_ADD(
		DATE_ADD(
			CURDATE(),
			INTERVAL -cast(RAND()*(4-1)+1 as unsigned)
			DAY),
		INTERVAL -cast(RAND()*(4-1)+1 as unsigned)
		MONTH),
	INTERVAL -cast(RAND()*(4-1)+1 as unsigned)
	YEAR)
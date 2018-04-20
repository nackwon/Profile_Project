// member start//
drop table users;
drop sequence seq_users_no;


create table users (
  no number,
  name varchar2(80),
  email varchar2(100) unique not null,
  password varchar2(20) not null,
  gender  varchar2(10),
  primary key(no)
);
UPDATE users SET name = '낙원', password = '12345', gender = 'female' WHERE no LIKE 11;
SELECT name, no FROM users WHERE email LIKE 'test' AND password LIKE 'test';
DELETE FROM users WHERE no LIKE 6;
create sequence seq_users_no
increment by 1
start with 1
nocache;

insert into users
values (seq_users_no.nextval, 'jimmy', 'iremys@gmail.com', '1234' , 'male');

insert into users
values (seq_users_no.nextval, 'Ȳ�Ͽ�', 'jungusung@gmail.com', '1234' , 'male');

select no, name, email, password, gender
from users
order by no desc;

select no, name, email, password, gender
from users
where no = 4;

select no, name, email, gender
from users
where email = 'iremys@gmail.com'
and password = '1234' ;

update users
set name = 'Ȳ�Ͽ�',
    email = 'leehry@gmail.com',
    password = '1234',
    gender ='female'
where no = 4;
// member end//

// Board start//
CREATE TABLE board(
	no number,
	title VARCHAR2(500),
	content VARCHAR2(4000),
	hit number,
	reg_date DATE,
	user_no NUMBER,
	PRIMARY KEY(no),
	CONSTRAINT c_board_fk FOREIGN KEY (user_no)
	REFERENCES users(no)
);

INSERT INTO board(seq_board_no.nextval, '안녕','안녕하세요',3,sysdate,SELECT e.no, b.user_no
FROM member e, board b
WHERE e.no = b.user_no);

CREATE SEQUENCE seq_board_no
INCREMENT BY 1
START WITH 1
NOCACHE;
SELECT *  FROM board;
DELETE FROM board WHERE no LIKE 3;
SELECT user_no FROM board WHERE board.user_no =
(SELECT no FROM users WHERE users.no = 9);
// Board end//

// GuestBook start //
CREATE TABLE guest_tbl(
	no number PRIMARY KEY,
	name VARCHAR2(80) not null,
	password VARCHAR2(20) not null,
	content VARCHAR2(2000) not null,
	reg_date DATE 
);

CREATE SEQUENCE seq_guest_no
INCREMENT BY 1
START WITH 1
NOCACHE;
SELECT * FROM guest_tbl;
// GuestBook end//

SELECT e.no, b.user_no
FROM member e, board b
WHERE e.no = b.no;



-- members 테이블
insert into members (userid, passwd, name, email)
values
    ('냐옹냐옹', 'f5e6dcb954d7d643742287b9eae4393ef01ebb1f37ab4a56be84f0e2fffddaba','987xyz','987xyz@abc123.kr'),
    ('abc123', '5ecf8d2cc410094e8b82dd0bc178a57f3aa1e80916689beb00fe56148b1b1256','abc123','abc123@abc123.kr'),
    ('987xyz', 'f5e6dcb954d7d643742287b9eae4393ef01ebb1f37ab4a56be84f0e2fffddaba','987xyz','987xyz@abc123.kr');

UPDATE members
SET name = '변경이름2'
WHERE mno = 1;

select * from members;

-- replys  테이블
insert into replys (comments, userid, ref, pno) values ('안녕하세요', 'abc123', 1, 3000);
insert into replys (comments, userid, ref, pno) values ( '방가방가!', '987xyz', 2, 3000);
insert into replys (comments, userid, ref, pno) values ( '오늘춥네여', '냐옹냐옹', 3, 3000);
insert into replys (comments, userid, ref, pno) values ( '날씨는요?', 'abc123', 2, 3000);

insert into gallerys (title, userid, contents, simgname) values ('삼삼데이 고기(소갈비살)','abc123','삼삼데이에 애들 데리고 소갈비살 먹었습니다

수입산인데 맛이 괜찮습니다.', 'cowmeat01_small.jpg');

insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat01.jpg',113223);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat02.jpg',189332);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat03.jpg',177652);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat04.jpg',129814);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat05.jpg',148542);
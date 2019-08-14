-- 学生选课系统sql
-- 学生表
CREATE TABLE Student
(
    sno       VARCHAR(3) PRIMARY KEY,
    sname     VARCHAR(8),
    Ssex      VARCHAR(2),
    sbirthday DATETIME,
    class     VARCHAR(5)
);

-- 表（二）Course（课程表）
CREATE TABLE Course
(
    cno   VARCHAR(5) PRIMARY KEY,
    cname VARCHAR(10),
    tno   CHAR(3)
);
-- 表（三）Score(成绩表)
CREATE TABLE Score
(
    sno    VARCHAR(3),
    cno    VARCHAR(5),
    degree DECIMAL(4, 1)
);
-- 表（四）Teacher(教师表)

CREATE TABLE Teacher
(
    tno       VARCHAR(3) PRIMARY KEY,
    tname     VARCHAR(4),
    tsex      VARCHAR(2),
    tbirthday DATETIME,
    prof      VARCHAR(6),
    depary    VARCHAR(10)
);

-- 18、 假设使用如下命令建立了一个Grade表：
CREATE TABLE Grade
(
    low  INT,
    upp  INT,
    rank CHAR(1)
);

INSERT INTO Student
VALUES (108, '曾华', '男', '1977-09-01', 95033);
INSERT INTO Student
VALUES (105, '匡明', '男', '1975-10-02', 95031);
INSERT INTO Student
VALUES (107, '王丽', '女', '1976-01-23', 95033);
INSERT INTO Student
VALUES (101, '李军', '男', '1976-02-20', 95033);
INSERT INTO Student
VALUES (109, '王芳', '女', '1975-02-10', 95031);
INSERT INTO Student
VALUES (103, '陆君', '男', '1974-06-03', 95031);

INSERT INTO Course
VALUES ('3-105', '计算机导论', 825);
INSERT INTO Course
VALUES ('3-245', '操作系统', 804);
INSERT INTO Course
VALUES ('6-166', '数字电路', 856);
INSERT INTO Course
VALUES ('9-888', '高等数学', 831);

INSERT INTO Score
VALUES (103, '3-245', 86);
INSERT INTO Score
VALUES (105, '3-245', 75);
INSERT INTO Score
VALUES (109, '3-245', 68);
INSERT INTO Score
VALUES (103, '3-105', 92);
INSERT INTO Score
VALUES (105, '3-105', 88);
INSERT INTO Score
VALUES (109, '3-105', 76);
INSERT INTO Score
VALUES (101, '3-105', 64);
INSERT INTO Score
VALUES (107, '3-105', 91);
INSERT INTO Score
VALUES (108, '3-105', 78);
INSERT INTO Score
VALUES (101, '6-166', 85);
INSERT INTO Score
VALUES (107, '6-166', 79);
INSERT INTO Score
VALUES (108, '6-166', 81);

INSERT INTO Teacher
VALUES (804, '李诚', '男', '1958-12-02', '副教授', '计算机系');
INSERT INTO Teacher
VALUES (856, '张旭', '男', '1969-03-12', '讲师', '电子工程系');
INSERT INTO Teacher
VALUES (825, '王萍', '女', '1972-05-05', '助教', '计算机系');
INSERT INTO Teacher
VALUES (831, '刘冰', '女', '1977-08-14', '助教', '电子工程系');

INSERT INTO Grade
VALUES (90, 100, 'A');
INSERT INTO Grade
VALUES (80, 89, 'b');
INSERT INTO Grade
VALUES (70, 79, 'C');
INSERT INTO Grade
VALUES (60, 69, 'D');
INSERT INTO Grade
VALUES (0, 59, 'E');

SELECT *
FROM Course;
SELECT *
FROM Student;
SELECT *
FROM Score;
SELECT *
FROM Teacher;
SELECT *
FROM Grade;

-- 1、 查询Student表中的所有记录的Sname、Ssex和Class列。
SELECT sname, Ssex, class
FROM Student;

-- 2、 查询教师所有的单位即不重复的Depart列。
SELECT DISTINCT depary
FROM Teacher;

-- 3、 查询Student表的所有记录。
SELECT *
FROM Student;

-- 4、 查询Score表中成绩在60到80之间的所有记录。
SELECT *
FROM Score
WHERE degree BETWEEN 60 AND 80;

-- 5、 查询Score表中成绩为85，86或88的记录。
SELECT *
FROM Score
WHERE degree IN (85, 86, 88);

-- 6、 查询Student表中“95031”班或性别为“女”的同学记录。
SELECT *
FROM Student
WHERE class = "95031"
   OR Ssex = '女';

-- 7、 以Class降序查询Student表的所有记录。
SELECT *
FROM Student
ORDER BY sno DESC;

-- 8、 以Cno升序、Degree降序查询Score表的所有记录。
SELECT *
FROM Score
ORDER BY cno;
SELECT *
FROM Score
ORDER BY degree DESC;

-- 9、 查询“95031”班的学生人数。
SELECT COUNT(*)
FROM Student
WHERE class = "95031";

-- 10、 查询Score表中的最高分的学生学号和课程号。（子查询或者排序）
SELECT sno, cno
FROM Score
WHERE degree = (SELECT MAX(degree) FROM Score);

-- 11、 查询每门课的平均成绩。
SELECT AVG(t.degree)
FROM Score t
GROUP BY t.cno;

-- 12、查询Score表中至少有5名学生选修的并以3开头的课程的平均分数。
SELECT AVG(degree) AS avgdegree
FROM Score
GROUP BY cno
HAVING cno = '3-105';

-- 13、查询分数大于70，小于90的Sno列。
SELECT sno
FROM Score
WHERE degree BETWEEN 70 AND 90;

-- 14、查询所有学生的Sname、Cno和Degree列。
SELECT Student.sname, Score.cno, degree
FROM Student,
     Score
WHERE Student.sno = Score.sno;

-- 15、查询所有学生的Sno、Cname和Degree列。
SELECT Student.sno, sname, Score.degree
FROM Student,
     Score
WHERE Student.sno = Score.sno;

-- 16、查询所有学生的Sname、Cname和Degree列。
SELECT sname, cname, degree
FROM Student
         JOIN Score ON Student.sno = Score.sno
         JOIN Course ON Score.cno = Course.cno;

-- 17、 查询“95033”班学生的平均分。
SELECT AVG(degree) AS avgdegree
FROM Score
WHERE sno IN (SELECT sno FROM Student WHERE class = '95033');


SELECT sno,
       cno,
       (CASE
            WHEN degree BETWEEN 90 AND 100 THEN 'A'
            WHEN degree BETWEEN 80 AND 89 THEN 'B'
            WHEN degree BETWEEN 70 AND 79 THEN 'C'
            WHEN degree BETWEEN 60 AND 69 THEN 'D'
            WHEN degree BETWEEN 0 AND 59 THEN 'E' END) AS 'rank'
FROM Score;

-- 现查询所有同学的Sno、Cno和rank列。
SELECT *
FROM Grade;

-- 19、  查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。
SELECT *
FROM Score
WHERE cno = '3-105'
  AND degree >
      (SELECT degree FROM Score WHERE sno = '109' AND cno = '3-105');

-- 20、查询Score中选学多门课程的同学中分数为非最高分成绩的记录。
SELECT t.sno
FROM Score t
WHERE t.degree <
      (SELECT MAX(t.degree) FROM Score t)
GROUP BY sno
HAVING COUNT(cno) > 1;

-- 21、 查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。
SELECT *
FROM Score
WHERE degree > (SELECT degree FROM Score WHERE sno = 109 AND cno = '3-105');

-- 22、查询和学号为108的同学同年出生的所有学生的Sno、Sname和Sbirthday列。
SELECT sno, sname, sbirthday
FROM Student
WHERE YEAR(sbirthday) =
      (SELECT YEAR(sbirthday) FROM Student WHERE sno = '108');

-- 23、查询“张旭“教师任课的学生成绩。
SELECT degree
FROM Score
WHERE cno =
      (SELECT cno
       FROM Course
                JOIN Teacher ON Teacher.tno = Course.tno
       WHERE tname = '张旭');

-- 24、查询选修某课程的同学人数多于5人的教师姓名。
SELECT tname
FROM Teacher
         JOIN Course ON Teacher.tno = Course.tno
WHERE cno IN (SELECT cno FROM Score GROUP BY cno HAVING COUNT(*) > 5);

-- 25、查询95033班和95031班全体学生的记录。
SELECT *
FROM Student
WHERE class IN ('95033', '95031');

-- 26、  查询存在有85分以上成绩的课程Cno.
SELECT DISTINCT cno
FROM Score
WHERE degree > 85;

-- 27、查询出“计算机系“教师所教课程的成绩表。
SELECT cno, sno, degree
FROM Score
WHERE cno IN
      (SELECT cno
       FROM Course,
            Teacher
       WHERE Course.tno = Teacher.tno
         AND Teacher.depary = '计算机系');

-- 28、查询“计算机系”与“电子工程系“不同职称的教师的Tname和Prof。
SELECT tname, prof
FROM Teacher a
WHERE prof NOT IN (SELECT prof FROM Teacher b WHERE b.depary != a.depary);

-- 29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的Cno、Sno和Degree,并按Degree从高到低次序排序。
SELECT *
FROM Score
WHERE cno = '3-105'
  AND degree
    > ANY (SELECT degree FROM Score WHERE cno = '3-245')
ORDER BY degree DESC;

-- 30、查询选修编号为“3-105”且成绩高于选修编号为“3-245”课程的同学的Cno、Sno和Degree.
SELECT cno, sno, degree
FROM Score
WHERE cno = '3-105'
  AND degree > ALL
      (SELECT degree FROM Score WHERE cno = '3-245')
ORDER BY degree DESC;

-- 31、 查询所有教师和同学的name、sex和birthday.
SELECT tname, tsex, tbirthday
FROM Teacher
UNION
SELECT sname, ssex, sbirthday
FROM Student;

-- 32、查询所有“女”教师和“女”同学的name、sex和birthday.
SELECT tname, tsex, tbirthday
FROM Teacher
WHERE tsex = '女'
UNION
SELECT sname, ssex, sbirthday
FROM Student
WHERE ssex = '女';

-- 33、 查询成绩比该课程平均成绩低的同学的成绩表。
SELECT *
FROM Score AS s1
WHERE degree < (SELECT AVG(degree)
                FROM Score AS s2
                GROUP BY cno
                HAVING s1.cno = s2.cno);

-- 34、 查询所有任课教师的Tname和Depart.
SELECT tname, a.depary
FROM Teacher a
WHERE EXISTS(SELECT * FROM Course b WHERE a.tno = b.tno);

-- 35 、 查询所有未讲课的教师的Tname和Depart.
SELECT tname, a.depary
FROM Teacher a
WHERE NOT EXISTS(SELECT * FROM Course b WHERE a.tno = b.tno);
SELECT tname, depary
FROM Teacher
WHERE tno NOT IN
      (SELECT tno FROM Course WHERE cno IN (SELECT DISTINCT cno FROM Score));

-- 36、查询至少有2名男生的班号。
SELECT class
FROM Student
GROUP BY class, ssex
HAVING ssex = '男'
   AND COUNT(ssex) > 1;

-- 37、查询Student表中不姓“王”的同学记录。
SELECT *
FROM Student
WHERE sno NOT IN (SELECT sno FROM Student WHERE sname LIKE '王%');

-- 38、查询Student表中每个学生的姓名和年龄。
SELECT sname, DATEDIFF(YEAR, Sbirthday, CURRENT_TIMESTAMP)
FROM Student;

-- 39、查询Student表中最大和最小的Sbirthday日期值。
SELECT sbirthday
FROM Student
ORDER BY sbirthday
Limit 1;
SELECT sbirthday
FROM Student
ORDER BY sbirthday DESC
Limit 1;

-- 40、以班号和年龄从大到小的顺序查询Student表中的全部记录。
SELECT *
FROM Student
ORDER BY class DESC, Sbirthday;

-- 41、查询“男”教师及其所上的课程。
SELECT tname, tsex, cname
FROM Teacher
         LEFT JOIN Course ON Course.tno = Teacher.tno
WHERE tsex = '男';

-- 42、查询最高分同学的Sno、Cno和Degree列。
SELECT *
FROM Score
ORDER BY degree DESC
LIMIT 1;

-- 43、查询和“李军”同性别的所有同学的Sname.
SELECT sname
FROM Student
WHERE Ssex =
      (SELECT Ssex FROM Student WHERE sname = '李军');

-- 44、查询和“李军”同性别并同班的同学Sname.
SELECT sname
FROM Student
WHERE ssex = (SELECT ssex FROM Student WHERE sname = '李军')
  AND class = (SELECT class FROM Student WHERE sname = '李军')
  AND sname NOT IN ('李军');

-- 45、查询所有选修“计算机导论”课程的“男”同学的成绩表。
SELECT *
FROM Score
WHERE sno IN (SELECT sno
              FROM Student
              WHERE Ssex = '男'
)
  AND cno = (SELECT cno FROM Course WHERE cname = '计算机导论');
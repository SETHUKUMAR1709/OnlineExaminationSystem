create database if not exists OnlineES;
use OnlineES;
CREATE TABLE IF NOT EXISTS teacher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS exam (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    total_marks INT NOT NULL,
    teacher_id BIGINT NOT NULL,

    CONSTRAINT fk_exam_teacher
        FOREIGN KEY (teacher_id)
        REFERENCES teacher(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    max_marks INT NOT NULL,
    exam_id BIGINT NOT NULL,

    CONSTRAINT fk_question_exam
        FOREIGN KEY (exam_id)
        REFERENCES exam(id)
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS exam_attempt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_date DATETIME NOT NULL,
    total_score INT DEFAULT 0,
    student_id BIGINT NOT NULL,
    exam_id BIGINT NOT NULL,

    CONSTRAINT fk_attempt_student
        FOREIGN KEY (student_id)
        REFERENCES student(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_attempt_exam
        FOREIGN KEY (exam_id)
        REFERENCES exam(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS exam_attempt_question (
    attempt_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer TEXT,
    score INT DEFAULT 0,
    PRIMARY KEY (attempt_id , question_id),
    CONSTRAINT fk_attemptquestion_attempt FOREIGN KEY (attempt_id)
        REFERENCES exam_attempt (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_attemptquestion_question FOREIGN KEY (question_id)
        REFERENCES question (id)
        ON DELETE CASCADE
);

select * from exam_attempt_question;

select * from student;

insert into student values(1,"Sethukumar", "Sethukumar@gmail.com");

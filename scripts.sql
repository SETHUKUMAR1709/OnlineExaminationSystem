-- ============================================================
-- Online Examination System – Database Schema & Seed Data
-- ============================================================

CREATE DATABASE IF NOT EXISTS onlinees;
USE onlinees;

-- Drop tables in reverse dependency order for clean re-creation
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS exam_attempt;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS exam;
DROP TABLE IF EXISTS user;

-- ============================================================
-- 1. USER TABLE (replaces old teacher / student tables)
-- ============================================================
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255),
    role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL
);

-- ============================================================
-- 2. EXAM TABLE
-- ============================================================
CREATE TABLE exam (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    total_marks INT NOT NULL,
    total_questions INT DEFAULT 0,
    published BOOLEAN DEFAULT FALSE,
    teacher_id BIGINT NOT NULL,

    CONSTRAINT fk_exam_teacher
        FOREIGN KEY (teacher_id) REFERENCES user(id) ON DELETE CASCADE
);

-- ============================================================
-- 3. QUESTION TABLE
-- ============================================================
CREATE TABLE question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    max_marks INT NOT NULL,
    type ENUM('MCQ', 'FILL_IN_THE_BLANK', 'SHORT_ANSWER', 'DESCRIPTIVE') NOT NULL,
    options TEXT,
    correct_answer VARCHAR(500),
    exam_id BIGINT NOT NULL,

    CONSTRAINT fk_question_exam
        FOREIGN KEY (exam_id) REFERENCES exam(id) ON DELETE CASCADE
);

-- ============================================================
-- 4. EXAM_ATTEMPT TABLE
-- ============================================================
CREATE TABLE exam_attempt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_date DATETIME NOT NULL,
    total_score INT DEFAULT 0,
    graded BOOLEAN DEFAULT FALSE,
    student_id BIGINT NOT NULL,
    exam_id BIGINT NOT NULL,

    CONSTRAINT fk_attempt_student
        FOREIGN KEY (student_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_attempt_exam
        FOREIGN KEY (exam_id) REFERENCES exam(id) ON DELETE CASCADE
);

-- ============================================================
-- 5. ANSWER TABLE (replaces old exam_attempt_question)
-- ============================================================
CREATE TABLE answer (
    attempt_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer TEXT,
    score INT DEFAULT NULL,
    graded_by BIGINT DEFAULT NULL,

    PRIMARY KEY (attempt_id, question_id),

    CONSTRAINT fk_answer_attempt
        FOREIGN KEY (attempt_id) REFERENCES exam_attempt(id) ON DELETE CASCADE,
    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE,
    CONSTRAINT fk_answer_grader
        FOREIGN KEY (graded_by) REFERENCES user(id) ON DELETE SET NULL
);


-- ============================================================
-- SEED DATA
-- ============================================================

-- Users: 1 admin, 2 teachers, 3 students
INSERT INTO user (name, email, password, role) VALUES
('Admin User',    'admin@exam.com',      'admin123',   'ADMIN'),
('Dr. Smith',     'smith@exam.com',      'teacher123', 'TEACHER'),
('Prof. Johnson', 'johnson@exam.com',    'teacher123', 'TEACHER'),
('Alice Brown',   'alice@student.com',   'student123', 'STUDENT'),
('Bob Davis',     'bob@student.com',     'student123', 'STUDENT'),
('Charlie Wilson', 'charlie@student.com','student123', 'STUDENT');

-- Exams (teacher_id 2 = Dr. Smith, teacher_id 3 = Prof. Johnson)
INSERT INTO exam (title, total_marks, total_questions, published, teacher_id) VALUES
('Java Fundamentals',        100, 4, TRUE,  2),
('Data Structures',          80,  4, TRUE,  2),
('Database Management',      60,  3, FALSE, 3);

-- Questions for "Java Fundamentals" (exam_id = 1)
INSERT INTO question (text, max_marks, type, options, correct_answer, exam_id) VALUES
('Which keyword is used to create a class in Java?',
    10, 'MCQ', '["class","Class","struct","type"]', 'class', 1),
('The default value of an int variable in Java is ___.',
    10, 'FILL_IN_THE_BLANK', NULL, '0', 1),
('Explain the concept of polymorphism in Java.',
    40, 'DESCRIPTIVE', NULL, NULL, 1),
('What is the difference between == and .equals() in Java?',
    40, 'SHORT_ANSWER', NULL, NULL, 1);

-- Questions for "Data Structures" (exam_id = 2)
INSERT INTO question (text, max_marks, type, options, correct_answer, exam_id) VALUES
('Which data structure uses LIFO?',
    20, 'MCQ', '["Queue","Stack","Array","LinkedList"]', 'Stack', 2),
('A binary tree with n nodes has ___ NULL pointers.',
    20, 'FILL_IN_THE_BLANK', NULL, 'n+1', 2),
('Describe the time complexity of quicksort in best, average, and worst case.',
    20, 'SHORT_ANSWER', NULL, NULL, 2),
('Write a Java method to reverse a singly linked list.',
    20, 'DESCRIPTIVE', NULL, NULL, 2);

-- Questions for "Database Management" (exam_id = 3)
INSERT INTO question (text, max_marks, type, options, correct_answer, exam_id) VALUES
('SQL stands for ___.',
    20, 'FILL_IN_THE_BLANK', NULL, 'Structured Query Language', 3),
('Which normal form eliminates partial dependencies?',
    20, 'MCQ', '["1NF","2NF","3NF","BCNF"]', '2NF', 3),
('Explain ACID properties of a transaction.',
    20, 'DESCRIPTIVE', NULL, NULL, 3);

-- Exam Attempts (student_id: 4 = Alice, 5 = Bob, 6 = Charlie)
INSERT INTO exam_attempt (attempt_date, total_score, graded, student_id, exam_id) VALUES
('2026-02-10 09:00:00', 85, TRUE,  4, 1),
('2026-02-10 10:00:00', 60, TRUE,  5, 1),
('2026-02-11 09:00:00', 70, FALSE, 6, 1),
('2026-02-11 14:00:00', 55, TRUE,  4, 2);

-- Answers for Alice's Java attempt (attempt_id = 1)
INSERT INTO answer (attempt_id, question_id, answer, score, graded_by) VALUES
(1, 1, 'class',       10, 2),
(1, 2, '0',           10, 2),
(1, 3, 'Polymorphism means many forms. In Java it is achieved through method overloading and overriding.', 35, 2),
(1, 4, '== checks reference equality, .equals() checks value equality.', 30, 2);

-- Answers for Bob's Java attempt (attempt_id = 2)
INSERT INTO answer (attempt_id, question_id, answer, score, graded_by) VALUES
(2, 1, 'Class',       0,  2),
(2, 2, '0',           10, 2),
(2, 3, 'Polymorphism allows objects to take many forms.', 25, 2),
(2, 4, 'They are the same.',                              25, 2);

-- Answers for Charlie's Java attempt (attempt_id = 3, ungraded)
INSERT INTO answer (attempt_id, question_id, answer, score, graded_by) VALUES
(3, 1, 'class',       10, NULL),
(3, 2, '0',           10, NULL),
(3, 3, 'Polymorphism is when a class can have multiple methods with the same name.', NULL, NULL),
(3, 4, '== is for primitives, .equals() is for objects.', NULL, NULL);

-- Answers for Alice's Data Structures attempt (attempt_id = 4)
INSERT INTO answer (attempt_id, question_id, answer, score, graded_by) VALUES
(4, 5, 'Stack',       20, 2),
(4, 6, 'n+1',         20, 2),
(4, 7, 'Best: O(n log n), Average: O(n log n), Worst: O(n^2)', 15, 2);

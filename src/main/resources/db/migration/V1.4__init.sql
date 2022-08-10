CREATE TABLE STUDENT(
                        STUDENT_ID SERIAL PRIMARY KEY,
                        STUDENT_NAME varchar(255)
);

CREATE TABLE COURSE(
                       COURSE_ID SERIAL PRIMARY KEY,
                       COURSE_NAME varchar(255)
);

CREATE TABLE STUDENT_COURSE (
                                ID SERIAL PRIMARY KEY,
                                STUDENT_ID INTEGER NOT NULL,
                                COURSE_ID  INTEGER NOT NULL,
                                FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(STUDENT_ID),
                                FOREIGN KEY (COURSE_ID) REFERENCES COURSE(COURSE_ID)
);
INSERT INTO profession (description, name) VALUES ('Other profession', 'Other');
INSERT INTO profession (description, name) VALUES ('Student profession', 'Student');
INSERT INTO profession (description, name) VALUES ('Professor profession', 'Professor');
INSERT INTO profession (description, name) VALUES ('Director profession', 'Director');

insert into users (username, password, email) values ('admin', '{bcrypt}$2a$10$ETTIa0AxPDpkHdaOrG6MkeKTCY/8PAE2RcgWN4p1rFo59p/1gx.R2', 'admin@director.com');
INSERT INTO users_profession(user_id, profession_id) values (1, 4);

insert into classroom (classroom_name, available, available_at, capacity, professor) values ('Aula Magna', false, '12:00:01', 180, 'Julian');
insert into classroom (classroom_name, available, available_at, capacity, professor) values ('Classroom1', true, '21:41:00', 28, '');
insert into classroom (classroom_name, available, available_at, capacity, professor) values ('Classroom2', false, '16:30:00', 35, 'Marcela');
insert into classroom (classroom_name, available, available_at, capacity, professor) values ('Classroom3', false, '13:00:00', 20, 'Lucas');
insert into classroom (classroom_name, available, available_at, capacity, professor) values ('Classroom12', false, '12:00:00', 35, 'Julian');

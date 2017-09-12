INSERT INTO public.users(id, email, password, reset_token, first_name, last_name, account_id)
VALUES (0,
        'email@test.com',
        '$2a$10$NzIplRGJz4hEr.BbW4gXWueEBVCdFvCMFPnmx1CKbWI0tjkBKw.Q6',
        NULL, 'Mike', 'Smith', NULL);


INSERT INTO skills (id, name) VALUES (0, 'Java');
INSERT INTO skills (id, name) VALUES (1, 'Hibernate');
INSERT INTO skills (id, name) VALUES (2, 'RESTful Services');
INSERT INTO skills (id, name) VALUES (3, 'Spring Framework');
INSERT INTO skills (id, name) VALUES (4, 'Angular 2');
INSERT INTO skills (id, name) VALUES (5, 'PostgresSQL');
INSERT INTO skills (id, name) VALUES (6, 'Apache Camel');
INSERT INTO skills (id, name) VALUES (7, 'Bash');
INSERT INTO skills (id, name) VALUES (8, 'DNS');
INSERT INTO skills (id, name) VALUES (9, 'Networking');
INSERT INTO skills (id, name) VALUES (10, 'Amazon Web Services');
INSERT INTO skills (id, name) VALUES (11, 'Java Enterprise Edition');
INSERT INTO skills (id, name) VALUES (12, 'Design Patterns');
INSERT INTO skills (id, name) VALUES (13, 'Scrum');
INSERT INTO skills (id, name) VALUES (14, 'SQL');
INSERT INTO skills (id, name) VALUES (15, 'Data Analysis');
INSERT INTO skills (id, name) VALUES (16, 'Programming');
INSERT INTO skills (id, name) VALUES (17, 'R');
INSERT INTO skills (id, name) VALUES (18, 'Hadoop');
INSERT INTO skills (id, name) VALUES (19, '.Net');
INSERT INTO skills (id, name) VALUES (20, 'Web Services');
INSERT INTO skills (id, name) VALUES (21, 'XML');
INSERT INTO skills (id, name) VALUES (22, 'Microsoft Sql Server');
INSERT INTO skills (id, name) VALUES (23, 'ASP.Net');

INSERT INTO candidates (id,
                        user_id,
                        name,
                        location,
                        email,
                        phone,
                        website,
                        desired_salary,
                        is_visa_required,
                        visa_type,
                        visa_notes,
                        created_at,
                        updated_at,
                        linkedin_profile)
  VALUES (0,
          0,
          'Himanshu Yadav',
          'Greater Boston Area',
          'himanshuy@outlook.com',
          '704906112',
          'https://www.himanshuy.com',
          '123456',
          false,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL);

INSERT INTO candidates (id,
                        user_id,
                        name,
                        location,
                        email,
                        phone,
                        website,
                        desired_salary,
                        is_visa_required,
                        visa_type,
                        visa_notes,
                        created_at,
                        updated_at,
                        linkedin_profile)
VALUES (1,
  0,
  'Fabian Ponce',
  'Greater Boston Area',
  'me@fabianponce.com',
  '2817319821',
  'https://www.fabianponce.com',
  '123456',
  false,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL);

INSERT INTO candidates (id,
                        user_id,
                        name,
                        location,
                        email,
                        phone,
                        website,
                        desired_salary,
                        is_visa_required,
                        visa_type,
                        visa_notes,
                        created_at,
                        updated_at,
                        linkedin_profile)

VALUES (2,
  0,
  'Anton Serhiychuk',
  'Greater Boston Area',
  'serhiychuk.anton@gmail.com',
  '',
  '',
  '123456',
  false,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL);

INSERT INTO candidates (id,
                        user_id,
                        name,
                        location,
                        email,
                        phone,
                        website,
                        desired_salary,
                        is_visa_required,
                        visa_type,
                        visa_notes,
                        created_at,
                        updated_at,
                        linkedin_profile)

VALUES (3,
  0,
  'James Hughes',
  'Cambridge, Massachusetts',
  'jph5972@rit.edu',
  '7169697287',
  'http://www.heytherejp.com',
  '123456',
  false,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL);

INSERT INTO candidates (id,
                        user_id,
                        name,
                        location,
                        email,
                        phone,
                        website,
                        desired_salary,
                        is_visa_required,
                        visa_type,
                        visa_notes,
                        created_at,
                        updated_at,
                        linkedin_profile)

VALUES (4,
  0,
  'Edgardo Robles',
  'Greater Boston Area',
  'edgardo.robles@outlook.com',
  '781-820-5995',
  '',
  '123456',
  false,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL);

-----

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (0, 0, 'Saylent', 'Senior Software Engineer', 'Greater Boston Area', 'Sep2016 - Present (5 months)');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (4, 0, 'Next Step Living', 'Senior Software Engineer', 'Greater Boston Area', 'March2016 - Sep2016 (6 months)');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (5, 0, 'Optaros', 'Senior Developer', 'Greater Boston Area', 'May2016 - March2016 (9 months)');


INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (0, 0, 'UP Technical University', '2001-2005', 'Computer Science', 'B. Tech', NULL);

INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 0);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 1);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 2);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 3);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 4);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 5);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (0, 6);

INSERT INTO public.notes(candidate_id, content, created_at, created_by_id)
VALUES (0, 'He is a great guy', '12/29/2016 19:40', 0);

-----

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (6, 1, 'Toast Inc', 'Senior DevOps Engineer', 'Boston,MA', 'Sep2016-current');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (7, 1, 'Burst', 'DevOps Engineer', 'Boston,MA', 'Apr2016-Sep2016');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (8, 1, 'Lanyon', 'System Engineer', 'Waltham, MA', 'Feb2014-Apr2016');


INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (1, 1, 'Rutgers University–Camden', '2010-2011', 'Computer Science', '', NULL);

INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (1, 7);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (1, 8);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (1, 9);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (1, 10);

INSERT INTO public.notes(candidate_id, content, created_at, created_by_id)
VALUES (1, 'He is a great guy', '12/29/2016 19:40', 0);

-----

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (9, 2, 'Saylent', 'Senior Software Engineer', 'Boston,MA', 'Oct2016-Present');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (10, 2, 'Burst', 'Senior Software Engineer', 'Boston,MA', 'Mar2016-Sep2016');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (11, 2, 'Loomis, Sayles & Company', 'Full Stack Engineer', 'Boston,MA', 'Aug2011-Mar2016');


INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (2, 2, 'National University ''Ivan Franko'', Lviv', '2001-2006', 'Applied Mathematics and Informatics', 'Master''s Degree', NULL);

INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (3, 2, 'Lviv Physics and Mathematics Lyceum', '1998-2001', 'Physics and Mathematics', '', NULL);

INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 3);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 11);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 12);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 13);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 2);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (2, 5);

INSERT INTO public.notes(candidate_id, content, created_at, created_by_id)
VALUES (2, 'He is a great guy', '12/29/2016 19:40', 0);

-----

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (12, 3, 'Amazon', 'Business Analyst', 'Cambridge, MA', 'Oct2016-Present');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (13, 3, 'Fidelity Investments', 'Data Analyst', 'Boston,MA', 'June2015-Oct2016');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (14, 3, 'Rochester Institute of Technology', 'Program Manager', 'New York Area', 'Jan2015-May2015');


INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (4, 3, 'Rochester Institute of Technology, NY', '2001-2006', 'Management Information Systems & Economics', 'Dual Bachelor''s of Science Degree', NULL);

INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (3, 14);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (3, 15);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (3, 16);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (3, 17);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (3, 18);

INSERT INTO public.notes(candidate_id, content, created_at, created_by_id)
VALUES (3, 'He is a great guy', '12/29/2016 19:40', 0);

-----

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (15, 4, 'Saylent', 'Principal Software Engineer', 'Franklin, MA', 'Jan2016-Present');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (16, 4, 'EMC', 'Principal Software Application Engineer', 'Hopkinton,MA', 'Apr2012-Jan2016');

INSERT INTO public.experience_detail(id, candidate_id, company_name, title, location, time_period)
VALUES (17, 4, 'Rochester Institute of Technology', 'Software Architect/Technical Manager', 'Charleston, SC', 'Aug2007-Apr2012');


INSERT INTO public.education_detail(id, candidate_id, school, dates_attended, fields_of_study, degree, name_of_course)
VALUES (5, 4, 'Northeastern University', '1989-1991', '', '', NULL);

INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (4, 19);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (4, 20);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (4, 21);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (4, 22);
INSERT INTO public.candidate_skill(candidate_id, skill_id) VALUES (4, 23);

INSERT INTO public.notes(candidate_id, content, created_at, created_by_id)
VALUES (4, 'He is a great guy', '12/29/2016 19:40', 0);

-----
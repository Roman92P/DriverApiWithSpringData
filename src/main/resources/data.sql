INSERT INTO topic (id, topic_description) VALUES ('1','Prędkość');
INSERT INTO topic (id, topic_description) VALUES ('2','Prawo jazdy');
INSERT INTO topic (id, topic_description) VALUES ('3','Znaki');
INSERT INTO topic (id, topic_description) VALUES ('4','Uprawnienia');
INSERT INTO topic (id, topic_description) VALUES ('5','Pojazdy');
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('1','Title1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',1);
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('2','Title2','Lore ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',1);
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('3','Title3','Lor ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',2);
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('4','Title4','Lo ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',3);
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('5','Title5','Lipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',4);
INSERT INTO advice(id, advice_title, content, topic_id) VALUES ('6','Title6','psum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu. Donec ut aliquet arcu. Duis fringilla justo at lacus tempus porttitor. Quisque semper sem eu consequat tincidunt. Sed rutrum interdum massa, non fringilla urna fermentum in. Pellentesque ut velit congue, rhoncus dui non, tempor purus. Aliquam scelerisque, elit at condimentum tempus, tellus augue eleifend lorem, sollicitudin iaculis ex ante condimentum tellus. Nunc accumsan nunc libero, eget pellentesque eros pharetra accumsan. Aenean ultrices bibendum diam facilisis feugiat. Morbi vitae efficitur ex, in malesuada ligula. Curabitur dapibus faucibus est vel consequat.',5);
INSERT INTO advice(id, advice_title, content) VALUES ('7','Title7','test');
INSERT INTO advice (id, advice_title, content, topic_id,likes, shares ) VALUES ('8', 'Tytuł', 'Jakaś treść', '1','10','5');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content, topic_id)
VALUES ('1', '50', '55','60','65','Dozwolona prędkość w mieście do 23:00?','1');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content, topic_id)
VALUES ('2', 'Kat B', 'Kat A','Kat C','Kat C+E','Aby prowadzić ciężarówkę musisz mieć?','2');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content, topic_id)
VALUES ('3', 'Ustąp pierwszeństwo', 'Jakiś znak','Nie znak','Brak parkingu','Żółty trójkąt to znak?','3');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content, topic_id)
VALUES ('4', 'OC', 'AC','NNW','SZYBY','Jakie ubezpieczenie jest obowiązkowe?','4');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content, topic_id)
VALUES ('5', 'Rower', 'Samochód osobowy','Motor','Ciężarówka','Kto nie może wjechać na autostradę?','5');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content)
VALUES ('6', '140', '100','90','120','Dozwolona prędkość na autostradzie?');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content,topic_id)
VALUES ('7', '90', '100','95','80','Dozwolona prędkość poza miastem?','1');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content,topic_id)
VALUES ('8', '30', '50','95','80','Minimalna prędkość pod mostem?','1');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content,topic_id)
VALUES ('9', 'Nakaz jazdy w prawo', 'Zakaz parkingu po prawej stronie','Zakaz postoju po prawej stronie','Nakaz jazdy w prawo','Okrągły niebieski znak ze strzałką w prawo?','3');
INSERT INTO question(id, correct_answer, possible_answer_one, possible_answer_three, possible_answer_two, question_content,topic_id)
VALUES ('10', 'Okrągły biały znak w czerwonej ramce', 'Okrągły biały znak w czerwonej ramce','Okrągły czerwony znak w białej ramce','Żółty trójkąt','Zakaz ruchu w obie strony?','3');
INSERT INTO training(id, training_title, topic_id) VALUES ('1','Dozwolona prędkość','1');
INSERT INTO training_questions(training_id, questions_id) VALUES ('1','1');
INSERT INTO training_questions(training_id, questions_id) VALUES ('1','9');
INSERT INTO training_questions(training_id, questions_id) VALUES ('1','10');
INSERT INTO training(id, training_title, topic_id) VALUES ('2','Znaki nakazu','3');
INSERT INTO training_questions(training_id, questions_id) VALUES ('2','8');
INSERT INTO training_questions(training_id, questions_id) VALUES ('2','9');
INSERT INTO training_questions(training_id, questions_id) VALUES ('2','10');
INSERT INTO role(id, role) VALUES ('1','ROLE_ADMIN');
INSERT INTO role(id, role) VALUES ('2','ROLE_USER');
INSERT INTO users(id, username, password, enabled) VALUES ('1','Roman','$2a$10$t6Lc6TpXVga/R0tQL3tea.ta596VnQrKVppkHchEN2Tf6.6jdwKr2', true);
INSERT INTO users(id, username, password, enabled) VALUES ('2','RomanDwa','$2a$10$t6Lc6TpXVga/R0tQL3tea.ta596VnQrKVppkHchEN2Tf6.6jdwKr2', true);
INSERT INTO users_complete_trainings(users_id, complete_trainings_id) VALUES ('1', '1');
INSERT INTO users_complete_trainings(users_id, complete_trainings_id) VALUES ('2', '2');
INSERT INTO user_role(user_id, role_id) VALUES ('1','1');
INSERT INTO user_role(user_id, role_id) VALUES ('1','2');
INSERT INTO user_role(user_id, role_id) VALUES ('2','1');
INSERT INTO user_role(user_id, role_id) VALUES ('2','2');
UPDATE advice SET training_id ='1'WHERE id ='1';
UPDATE advice SET training_id ='2'WHERE id ='2';
INSERT INTO forum_question(id, ask_time, text, user_id) VALUES ('1',"Sat Jul 17 23:02:29 CEST 2021",'Pytanie?','1');
#drop database crisp;
#delete from testing_history;
#alter table testing_history auto_increment = 1;

select * from testing_history;

INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(1,"VMC",Date '2020-04-01',"Postive");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(1,"VMC",Date '2020-04-11',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(1,"VMC",Date '2020-05-01',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(1,"VMC",Date '2020-05-21',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(2,"VMC",Date '2020-04-01',"Postive");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(3,"VMC",Date '2020-04-01',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(3,"VMC",Date '2020-04-29',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(3,"VMC",Date '2020-05-06',"Negative");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(2,"VMC",Date '2020-04-01',"Postive");
INSERT INTO testing_history(person_id,hospital,testing_date,result) VALUES(4,"VMC",Date '2020-04-01',"Negative");
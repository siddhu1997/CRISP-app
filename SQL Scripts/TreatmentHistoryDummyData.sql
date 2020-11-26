#drop database crisp;
#delete from treatment_history;

#UPDATE treatment_history SET admission_date ='2020-10-11',treatment_details='Updated... 'WHERE person_id=9;

select * from treatment_history;

INSERT INTO treatment_history VALUES(1,"lorem ipsum",Date '2020-10-03',Date '2020-12-01',null);
INSERT INTO treatment_history VALUES(2,"lorem ipsum",Date '2020-12-01',null,Date '2020-12-01');
INSERT INTO treatment_history VALUES(3,"lorem ipsum",Date '2020-10-03',null,null);
INSERT INTO treatment_history VALUES(4,"lorem ipsum",Date '2020-11-01',Date '2020-12-01',Date '2020-12-01');
INSERT INTO treatment_history VALUES(5,"lorem ipsum",Date '2020-10-01',Date '2020-12-01',null);
INSERT INTO treatment_history VALUES(6,"lorem ipsum",Date '2020-11-11',null,null);
INSERT INTO treatment_history VALUES(7,"lorem ipsum",Date '2020-07-01',Date '2020-12-01',Date '2020-12-01');
INSERT INTO treatment_history VALUES(8,"lorem ipsum",Date '2020-10-01',Date '2020-12-01',null);
INSERT INTO treatment_history VALUES(9,"lorem ipsum",null,null,null);
INSERT INTO treatment_history VALUES(10,"lorem ipsum",Date '2020-10-01',Date '2020-12-01',Date '2020-12-01');

CREATE DATABASE crisp;
USE crisp;

#Person Table
CREATE TABLE person(
person_id int AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
dob date,
phone_number varchar(10),
blood_group varchar(5),
location varchar(20),
password varchar(20));

#Treatment History Table
CREATE TABLE treatment_history(
person_id int,
treatment_details varchar(200),
admission_date date,
recovered_date date,
death_date date,
FOREIGN KEY(person_id) REFERENCES person(person_id));

#Testing History Table
CREATE TABLE testing_history(
testing_id int PRIMARY KEY AUTO_INCREMENT,
person_id int,
hospital varchar(10),
testing_date date,
result varchar(200),
FOREIGN KEY(person_id) REFERENCES person(person_id));

#Donation Request Table
CREATE TABLE donation_request(
req_id int PRIMARY KEY AUTO_INCREMENT,
person_id int,
donor_remarks varchar(500),
admin_remarks varchar(500),
stats varchar(10),
FOREIGN KEY(person_id) REFERENCES person(person_id));
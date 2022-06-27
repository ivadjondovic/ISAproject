insert into user_entity (id, username, password, user_type, has_signed_in, last_password_reset_date) values
(100000, 'boatowner@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 0, false, '2020-01-01 10:10:11'),
(200000, 'cottageowner@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 1, false, '2020-01-01 10:10:11'),
(300000, 'fishinginstructor@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 2, false, '2020-01-01 10:10:11'),
(400000, 'systemadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 3, false, '2020-01-01 10:10:11'),
(500000, 'client@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K',4, false, '2020-01-01 10:10:11');

insert into boat_owner (id, first_name, last_name, address, city, country, phone_number, request_status, user_id) values
(100000,'Marko','Markovic','Jovanovska 3','Beograd','Srbija','3342432', 'CONFIRMED', 100000);

insert into cottage_owner (id, first_name, last_name, address, city, country, phone_number, request_status, user_id) values
(100000,'Ana','Maric','Sonje Marinkovic 12','Novi Sad','Srbija','432423', 'CONFIRMED', 200000);

insert into fishing_instructor (id, first_name, last_name, address, city, country, phone_number,request_status, user_id) values
(100000,'Ksenija','Ilic','Marsala Tita 22','Janka Veselinovica','Srbija','4342432342','CONFIRMED', 300000);

insert into system_admin (id, user_id) values
(100000, 400000);

insert into client (id, user_id) values
(100000, 500000);


insert into user_entity (id, username, password, first_name, last_name, address, city, country, phone_number, user_type) values
(1, 'boatowner@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Marko','Markovic','Jovanovska 3','Beograd','Srbija','3342432', 0),
(2, 'cottageowner@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Ana','Maric','Sonje Marinkovic 12','Novi Sad','Srbija','432423', 1),
(3, 'fishinginstructor@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Ksenija','Ilic','Marsala Tita 22','Janka Veselinovica','Srbija','4342432342', 2),
(4, 'systemadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Jovana','Gajic','Fruskogorska','Kopernikova 5','Srbija','2344242',  3),
(5, 'client@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Marija','Jovanic','Maksima Gorkog 4','Novi Sad','Srbija','43242',4);

insert into boat_owner (id, user_id) values
(1, 1);

insert into cottage_owner (id, user_id) values
(1, 2);

insert into fishing_instructor (id, user_id) values
(1, 3);

insert into system_admin (id, user_id) values
(1, 4);

insert into client (id, user_id) values
(1, 5);
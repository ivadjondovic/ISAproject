insert into user_entity (id, username, password, user_type, has_signed_in, last_password_reset_date, requested_for_deletion, reason_for_deletion) values
(100000, 'boatowner@gmail.com', '$2a$10$G4wH4xrOjnhxtDVkpFd28uJSIpTHCAYRKDyJlR3gT4DkAl5WsN3d6', 0, false, '2020-01-01 10:10:11', false, ''),
(200000, 'cottageowner@gmail.com', '$2a$10$G4wH4xrOjnhxtDVkpFd28uJSIpTHCAYRKDyJlR3gT4DkAl5WsN3d6', 1, false, '2020-01-01 10:10:11', false, ''),
(300000, 'fishinginstructor@gmail.com', '$2a$10$G4wH4xrOjnhxtDVkpFd28uJSIpTHCAYRKDyJlR3gT4DkAl5WsN3d6', 2, false, '2020-01-01 10:10:11', false, ''),
(400000, 'systemadmin@gmail.com', '$2a$10$G4wH4xrOjnhxtDVkpFd28uJSIpTHCAYRKDyJlR3gT4DkAl5WsN3d6', 3, false, '2020-01-01 10:10:11', false, ''),
(500000, 'client@gmail.com', '$2a$10$G4wH4xrOjnhxtDVkpFd28uJSIpTHCAYRKDyJlR3gT4DkAl5WsN3d6',4, false, '2020-01-01 10:10:11', false, '');

insert into boat_owner (id, first_name, last_name, address, city, country, phone_number, request_status, user_id) values
(100000, 'Marko', 'Markovic', 'Jovanovska 3', 'Beograd', 'Srbija', '3342432', 'CONFIRMED', 100000);

insert into cottage_owner (id, first_name, last_name, address, city, country, phone_number, request_status, user_id) values
(100000, 'Ana', 'Maric', 'Sonje Marinkovic 12', 'Novi Sad', 'Srbija', '432423', 'CONFIRMED', 200000);

insert into fishing_instructor (id, first_name, last_name, address, city, country, phone_number,request_status, user_id) values
(100000, 'Ksenija', 'Ilic', 'Marsala Tita 22', 'Janka Veselinovica', 'Srbija', '4342432342', 'CONFIRMED', 300000);

insert into system_admin (id, user_id) values
(100000, 400000);

insert into client (id, user_id, first_name, last_name) values
(100000, 500000, 'Marija', 'Markovic');

insert into boat (id, name, type, length, engine_number, engine_power, max_speed, address, description, capacity, rules_of_conduct, price_list, fishing_equipment, cancellation_reservation_fee, boat_owner_id) values
(100000, 'Marina', 'SAILING_BOAT', '100', 100, 100, '80', 'Marka Markovica 19', 'desc', 20, 'Pravila ponasanja', 'Pricelist', 'Fishing equipment', 1, 100000),
(200000, 'Anita', 'CARGO_SHIP', '100', 100, 100, '80','Marka Markovica 19', 'desc', 20, 'Pravila ponasanja', 'Pricelist', 'Fishing equipment', 2, 100000);

insert into cottage (id, name, address, description, rules_of_conduct, price_list, other_information, number_of_rooms, number_of_beds_per_room, cottage_owner_id) values
(100000, 'Blue house', 'Sarajevska 3', 'Description', 'Pravila ponasanja', 'Pricelist', 'Other', 1, '2', 100000 ),
(200000, 'Yellow house', 'Prvomajska 39', 'Description', 'Pravila ponasanja', 'Pricelist', 'Other', 2, '2, 3', 100000);


insert into quick_reservation (id, start_time_quick_reservation, end_time_quick_reservation, start_date_quick_reservation, end_date_quick_reservation, max_number_of_people, price, expires_in, cottage_id, boat_id, reservation_type, service_id) values
(100000, '08:00', '08:00', '2022-08-08', '2021-08-20', 10, 1000, 2, null, 100000, 'BOAT', 100000),
(200000, '08:00', '08:00', '2021-09-01', '2021-09-04', 3, 200, 1, 100000, null, 'COTTAGE', 100000);

insert into additional_service(id, tag, quick_reservation_id) values
(100000, 'Wi-Fi', 100000),
(200000, 'Pet Friendy', 100000),
(300000, 'Parking', 200000);

insert into subscribed_clients_boats(boat_id, client_id) values
(100000, 100000);

insert into subscribed_clients_cottages(cottage_id, client_id) values
(100000, 100000);

insert into reservation (id, start_time_reservation, end_time_reservation, start_date_reservation, end_date_reservation, cottage_id, boat_id, reservation_type, reservation_status, service_id) values
(100000, '08:00', '08:00', '2022-08-08', '2021-08-20',  null, 100000, 'BOAT', 'AVAILABLE', 100000),
(200000, '08:00', '08:00', '2020-08-08', '2020-08-20',  null, 100000, 'BOAT', 'AVAILABLE', 100000),
(300000, '08:00', '08:00', '2021-09-01', '2021-09-04', 100000, null, 'COTTAGE', 'RESERVED', 100000);

insert into navigation_equipment (id, name, boat_id) values
(100000, 'VHF_RADIO', 100000),
(200000, 'FISH_FINDER', 100000),
(300000, 'GPS', 200000);




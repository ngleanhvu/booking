-- country
INSERT INTO country(id, code, name) VALUES (1,"VN", "Việt Nam");
-- city
INSERT INTO city (id, name, country_id) VALUES
                                            (1,'Hà Nội', 1),
                                            (2,'Hồ Chí Minh', 1),
                                            (3,'Hải Phòng', 1),
                                            (4,'Đà Nẵng', 1),
                                            (5,'Cần Thơ', 1),
                                            (6,'An Giang', 1),
                                            (7,'Bà Rịa - Vũng Tàu', 1),
                                            (8,'Bắc Giang', 1),
                                            (9,'Bắc Kạn', 1),
                                            (10,'Bạc Liêu', 1),
                                            (11,'Bắc Ninh', 1),
                                            (12,'Bến Tre', 1),
                                            (13,'Bình Định', 1),
                                            (14,'Bình Dương', 1),
                                            (15,'Bình Phước', 1),
                                            (16,'Bình Thuận', 1),
                                            (17,'Cà Mau', 1),
                                            (18,'Cao Bằng', 1),
                                            (19,'Đắk Lắk', 1),
                                            (20,'Đắk Nông', 1),
                                            (21,'Điện Biên', 1),
                                            (22,'Đồng Nai', 1),
                                            (23,'Đồng Tháp', 1),
                                            (24,'Gia Lai', 1),
                                            (25,'Hà Giang', 1),
                                            (26,'Hà Nam', 1),
                                            (27,'Hà Tĩnh', 1),
                                            (28,'Hải Dương', 1),
                                            (29,'Hậu Giang', 1),
                                            (30,'Hòa Bình', 1),
                                            (31,'Hưng Yên', 1),
                                            (32,'Khánh Hòa', 1),
                                            (33,'Kiên Giang', 1),
                                            (34,'Kon Tum', 1),
                                            (35,'Lai Châu', 1),
                                            (36,'Lâm Đồng', 1),
                                            (37,'Lạng Sơn', 1),
                                            (38,'Lào Cai', 1),
                                            (39,'Long An', 1),
                                            (40,'Nam Định', 1),
                                            (41,'Nghệ An', 1),
                                            (42,'Ninh Bình', 1),
                                            (43,'Ninh Thuận', 1),
                                            (44,'Phú Thọ', 1),
                                            (45,'Phú Yên', 1),
                                            (46,'Quảng Bình', 1),
                                            (47,'Quảng Nam', 1),
                                            (48,'Quảng Ngãi', 1),
                                            (49,'Quảng Ninh', 1),
                                            (50,'Quảng Trị', 1),
                                            (51,'Sóc Trăng', 1),
                                            (52,'Sơn La', 1),
                                            (53,'Tây Ninh', 1),
                                            (54,'Thái Bình', 1),
                                            (55,'Thái Nguyên', 1),
                                            (56,'Thanh Hóa', 1),
                                            (57,'Thừa Thiên Huế', 1),
                                            (58,'Tiền Giang', 1),
                                            (59,'Trà Vinh', 1),
                                            (60,'Tuyên Quang', 1),
                                            (61,'Vĩnh Long', 1),
                                            (62,'Vĩnh Phúc', 1),
                                            (63,'Yên Bái', 1);
-- district (Ho Chi Minh City)
INSERT INTO district (id, name, city_id) VALUES
                                             (1, 'Quận 1', 2),
                                             (2, 'Quận 3', 2),
                                             (3, 'Quận 4', 2),
                                             (4, 'Quận 5', 2),
                                             (5, 'Quận 6', 2),
                                             (6, 'Quận 7', 2),
                                             (7, 'Quận 8', 2),
                                             (8, 'Quận 10', 2),
                                             (9, 'Quận 11', 2),
                                             (10, 'Quận 12', 2),
                                             (11, 'Quận Bình Tân', 2),
                                             (12, 'Quận Bình Thạnh', 2),
                                             (13, 'Quận Gò Vấp', 2),
                                             (14, 'Quận Phú Nhuận', 2),
                                             (15, 'Quận Tân Bình', 2),
                                             (16, 'Quận Tân Phú', 2),
                                             (17, 'Thành phố Thủ Đức', 2),
                                             (18, 'Huyện Bình Chánh', 2),
                                             (19, 'Huyện Cần Giờ', 2),
                                             (20, 'Huyện Củ Chi', 2),
                                             (21, 'Huyện Hóc Môn', 2),
                                             (22, 'Huyện Nhà Bè', 2);
-- ward (Ho Chi Minh City)
-- district 1
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường Bến Nghé', 1),
                                         ('Phường Bến Thành', 1),
                                         ('Phường Cô Giang', 1),
                                         ('Phường Cầu Kho', 1),
                                         ('Phường Cầu Ông Lãnh', 1),
                                         ('Phường Đa Kao', 1),
                                         ('Phường Nguyễn Cư Trinh', 1),
                                         ('Phường Nguyễn Thái Bình', 1),
                                         ('Phường Phạm Ngũ Lão', 1),
                                         ('Phường Tân Định', 1);
-- Thu Duc City
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường Hiệp Bình Chánh', 17),
                                         ('Phường Hiệp Bình Phước', 17),
                                         ('Phường Linh Trung', 17),
                                         ('Phường Linh Xuân', 17),
                                         ('Phường Linh Chiểu', 17),
                                         ('Phường Linh Đông', 17),
                                         ('Phường Linh Tây', 17),
                                         ('Phường Trường Thọ', 17),
                                         ('Phường Bình Chiểu', 17),
                                         ('Phường Tam Bình', 17),
                                         ('Phường Tam Phú', 17),
                                         ('Phường Thảo Điền', 17),
                                         ('Phường An Phú', 17),
                                         ('Phường An Khánh', 17),
                                         ('Phường Bình An', 17),
                                         ('Phường Bình Trưng Đông', 17),
                                         ('Phường Bình Trưng Tây', 17),
                                         ('Phường Cát Lái', 17),
                                         ('Phường Thạnh Mỹ Lợi', 17),
                                         ('Phường Phú Hữu', 17);
-- district 3
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 2),
                                         ('Phường 2', 2),
                                         ('Phường 3', 2),
                                         ('Phường 4', 2),
                                         ('Phường 5', 2),
                                         ('Phường 6', 2),
                                         ('Phường 7', 2),
                                         ('Phường 8', 2),
                                         ('Phường 9', 2),
                                         ('Phường 10', 2),
                                         ('Phường 11', 2),
                                         ('Phường 12', 2),
                                         ('Phường 13', 2),
                                         ('Phường 14', 2);
-- district 4
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 3),
                                         ('Phường 2', 3),
                                         ('Phường 3', 3),
                                         ('Phường 4', 3),
                                         ('Phường 5', 3),
                                         ('Phường 6', 3),
                                         ('Phường 8', 3),
                                         ('Phường 9', 3),
                                         ('Phường 10', 3),
                                         ('Phường 12', 3),
                                         ('Phường 13', 3),
                                         ('Phường 14', 3),
                                         ('Phường 15', 3),
                                         ('Phường 16', 3),
                                         ('Phường 18', 3);
-- district 5
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 4),
                                         ('Phường 2', 4),
                                         ('Phường 3', 4),
                                         ('Phường 4', 4),
                                         ('Phường 5', 4),
                                         ('Phường 6', 4),
                                         ('Phường 7', 4),
                                         ('Phường 8', 4),
                                         ('Phường 9', 4),
                                         ('Phường 10', 4),
                                         ('Phường 11', 4),
                                         ('Phường 12', 4),
                                         ('Phường 13', 4),
                                         ('Phường 14', 4),
                                         ('Phường 15', 4);
-- district 6
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 5),
                                         ('Phường 2', 5),
                                         ('Phường 3', 5),
                                         ('Phường 4', 5),
                                         ('Phường 5', 5),
                                         ('Phường 6', 5),
                                         ('Phường 7', 5),
                                         ('Phường 8', 5),
                                         ('Phường 9', 5),
                                         ('Phường 10', 5),
                                         ('Phường 11', 5),
                                         ('Phường 12', 5),
                                         ('Phường 13', 5),
                                         ('Phường 14', 5);
-- district 7
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường Tân Phú', 6),
                                         ('Phường Tân Phong', 6),
                                         ('Phường Tân Thuận Đông', 6),
                                         ('Phường Tân Thuận Tây', 6),
                                         ('Phường Tân Kiểng', 6),
                                         ('Phường Tân Hưng', 6),
                                         ('Phường Bình Thuận', 6),
                                         ('Phường Phú Mỹ', 6),
                                         ('Phường Phú Thuận', 6);
-- district 8
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 7),
                                         ('Phường 2', 7),
                                         ('Phường 3', 7),
                                         ('Phường 4', 7),
                                         ('Phường 5', 7),
                                         ('Phường 6', 7),
                                         ('Phường 7', 7),
                                         ('Phường 8', 7),
                                         ('Phường 9', 7),
                                         ('Phường 10', 7),
                                         ('Phường 11', 7),
                                         ('Phường 12', 7),
                                         ('Phường 13', 7),
                                         ('Phường 14', 7),
                                         ('Phường 15', 7),
                                         ('Phường 16', 7);
-- district 10
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 8),
                                         ('Phường 2', 8),
                                         ('Phường 3', 8),
                                         ('Phường 4', 8),
                                         ('Phường 5', 8),
                                         ('Phường 6', 8),
                                         ('Phường 7', 8),
                                         ('Phường 8', 8),
                                         ('Phường 9', 8),
                                         ('Phường 10', 8),
                                         ('Phường 11', 8),
                                         ('Phường 12', 8),
                                         ('Phường 13', 8),
                                         ('Phường 14', 8);
-- district 11
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 9),
                                         ('Phường 2', 9),
                                         ('Phường 3', 9),
                                         ('Phường 4', 9),
                                         ('Phường 5', 9),
                                         ('Phường 6', 9),
                                         ('Phường 7', 9),
                                         ('Phường 8', 9),
                                         ('Phường 9', 9),
                                         ('Phường 10', 9),
                                         ('Phường 11', 9),
                                         ('Phường 12', 9),
                                         ('Phường 13', 9),
                                         ('Phường 14', 9),
                                         ('Phường 15', 9),
                                         ('Phường 16', 9);
-- district 12
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường An Phú Đông', 10),
                                         ('Phường Đông Hưng Thuận', 10),
                                         ('Phường Hiệp Thành', 10),
                                         ('Phường Tân Chánh Hiệp', 10),
                                         ('Phường Tân Hưng Thuận', 10),
                                         ('Phường Tân Thới Hiệp', 10),
                                         ('Phường Tân Thới Nhất', 10),
                                         ('Phường Thạnh Lộc', 10),
                                         ('Phường Thạnh Xuân', 10),
                                         ('Phường Thới An', 10),
                                         ('Phường Trung Mỹ Tây', 10);
-- district Binh Tan
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường An Lạc', 11),
                                         ('Phường An Lạc A', 11),
                                         ('Phường Bình Hưng Hòa', 11),
                                         ('Phường Bình Hưng Hòa A', 11),
                                         ('Phường Bình Hưng Hòa B', 11),
                                         ('Phường Bình Trị Đông', 11),
                                         ('Phường Bình Trị Đông A', 11),
                                         ('Phường Bình Trị Đông B', 11);
-- district Binh Thanh
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 12),
                                         ('Phường 2', 12),
                                         ('Phường 3', 12),
                                         ('Phường 5', 12),
                                         ('Phường 6', 12),
                                         ('Phường 7', 12),
                                         ('Phường 11', 12),
                                         ('Phường 12', 12),
                                         ('Phường 13', 12),
                                         ('Phường 14', 12),
                                         ('Phường 15', 12),
                                         ('Phường 21', 12);
-- district Go Vap
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 13),
                                         ('Phường 3', 13),
                                         ('Phường 4', 13),
                                         ('Phường 5', 13),
                                         ('Phường 6', 13),
                                         ('Phường 7', 13),
                                         ('Phường 8', 13),
                                         ('Phường 9', 13),
                                         ('Phường 10', 13),
                                         ('Phường 11', 13),
                                         ('Phường 12', 13),
                                         ('Phường 13', 13),
                                         ('Phường 14', 13);
-- district Phu Nhuan
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 14),
                                         ('Phường 2', 14),
                                         ('Phường 3', 14),
                                         ('Phường 4', 14),
                                         ('Phường 5', 14),
                                         ('Phường 7', 14),
                                         ('Phường 8', 14),
                                         ('Phường 9', 14),
                                         ('Phường 10', 14);
-- district Tan Binh
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường 1', 15),
                                         ('Phường 2', 15),
                                         ('Phường 3', 15),
                                         ('Phường 4', 15),
                                         ('Phường 5', 15),
                                         ('Phường 6', 15),
                                         ('Phường 7', 15),
                                         ('Phường 8', 15),
                                         ('Phường 9', 15),
                                         ('Phường 10', 15),
                                         ('Phường 11', 15),
                                         ('Phường 12', 15);
-- district Tan Phu
INSERT INTO ward (name, district_id) VALUES
                                         ('Phường Tân Sơn Nhì', 16),
                                         ('Phường Tây Thạnh', 16),
                                         ('Phường Sơn Kỳ', 16),
                                         ('Phường Tân Quý', 16),
                                         ('Phường Tân Thành', 16),
                                         ('Phường Phú Thạnh', 16),
                                         ('Phường Phú Trung', 16),
                                         ('Phường Hòa Thạnh', 16),
                                         ('Phường Hiệp Tân', 16);
-- district Binh Chanh
INSERT INTO ward (name, district_id) VALUES
                                         ('Xã An Phú Tây', 18),
                                         ('Xã Bình Chánh', 18),
                                         ('Xã Đa Phước', 18),
                                         ('Xã Hưng Long', 18),
                                         ('Xã Phạm Văn Hai', 18),
                                         ('Xã Tân Kiên', 18),
                                         ('Xã Tân Quý Tây', 18),
                                         ('Xã Vĩnh Lộc A', 18),
                                         ('Xã Vĩnh Lộc B', 18);
-- district Can Gio
INSERT INTO ward (name, district_id) VALUES
                                         ('Thị trấn Cần Thạnh', 19),
                                         ('Xã An Thới Đông', 19),
                                         ('Xã Bình Khánh', 19),
                                         ('Xã Long Hòa', 19),
                                         ('Xã Lý Nhơn', 19),
                                         ('Xã Tam Thôn Hiệp', 19),
                                         ('Xã Thạnh An', 19);
-- district Cu Chi
INSERT INTO ward (name, district_id) VALUES
                                         ('Thị trấn Củ Chi', 20),
                                         ('Xã An Nhơn Tây', 20),
                                         ('Xã Bình Mỹ', 20),
                                         ('Xã Nhuận Đức', 20),
                                         ('Xã Phạm Văn Cội', 20),
                                         ('Xã Phú Hòa Đông', 20),
                                         ('Xã Phước Hiệp', 20),
                                         ('Xã Phước Thạnh', 20),
                                         ('Xã Tân An Hội', 20),
                                         ('Xã Tân Thạnh Đông', 20),
                                         ('Xã Tân Thông Hội', 20),
                                         ('Xã Trung An', 20);
-- district Hoc Mon
INSERT INTO ward (name, district_id) VALUES
                                         ('Thị trấn Hóc Môn', 21),
                                         ('Xã Bà Điểm', 21),
                                         ('Xã Đông Thạnh', 21),
                                         ('Xã Nhị Bình', 21),
                                         ('Xã Tân Hiệp', 21),
                                         ('Xã Tân Thới Nhì', 21),
                                         ('Xã Thới Tam Thôn', 21),
                                         ('Xã Xuân Thới Đông', 21),
                                         ('Xã Xuân Thới Sơn', 21),
                                         ('Xã Xuân Thới Thượng', 21);
-- district Nha Be
INSERT INTO ward (name, district_id) VALUES
                                         ('Thị trấn Nhà Bè', 22),
                                         ('Xã Hiệp Phước', 22),
                                         ('Xã Long Thới', 22),
                                         ('Xã Phú Xuân', 22),
                                         ('Xã Phước Kiển', 22),
                                         ('Xã Nhơn Đức', 22);














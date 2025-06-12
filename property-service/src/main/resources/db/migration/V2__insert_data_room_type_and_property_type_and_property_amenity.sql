-- Insert Property Types
INSERT INTO property_type (name, description) VALUES
              ('villa', 'Biệt thự cao cấp có hồ bơi'),
              ('homestay', 'Nhà dân sinh hoạt chung với gia chủ'),
              ('studio', 'Căn hộ studio nhỏ gọn'),
              ('hotel_room', 'Phòng khách sạn tiêu chuẩn'),
              ('house', 'Ngôi nhà riêng biệt cho gia đình'),
              ('resort', 'Khu nghỉ dưỡng cao cấp');

-- Insert Room Types
INSERT INTO room_type (name) VALUES
             ('entire_place'),      -- Toàn bộ căn hộ/nhà
             ('private_room'),      -- Phòng riêng
             ('shared_room'),       -- Phòng chung
             ('hotel_room'),        -- Phòng khách sạn
             ('shared_bathroom'),   -- Phòng riêng nhưng chung toilet
             ('studio_room'),       -- Phòng studio
             ('master_bedroom'),    -- Phòng master
             ('single_room'),       -- Phòng đơn
             ('double_room'),       -- Phòng đôi
             ('family_room'),       -- Phòng gia đình
             ('dormitory'),         -- Phòng tập thể
             ('suite'),             -- Phòng suite
             ('junior_suite'),      -- Phòng junior suite
             ('presidential_suite'), -- Phòng tổng thống
             ('connecting_rooms');   -- Phòng liền kề

INSERT INTO property_amenity (name) VALUES
            ('wifi'),
            ('air_conditioning'),
            ('heating'),
            ('fan'),
            ('bed_linens'),
            ('towels'),
            ('soap'),
            ('shampoo'),
            ('toilet_paper'),
            ('hair_dryer'),
            ('iron'),
            ('room_darkening_shades'),
            ('extra_pillows'),
            ('tv'),
            ('smart_tv'),
            ('netflix'),
            ('bluetooth_speaker'),
            ('karaoke'),
            ('kitchen'),
            ('refrigerator'),
            ('microwave'),
            ('electric_kettle'),
            ('coffee_maker'),
            ('toaster'),
            ('dishes_silverware'),
            ('cooking_basics'),
            ('dining_table'),
            ('water_filter'),
            ('water_heater'),
            ('queen_bed'),
            ('king_bed'),
            ('single_bed'),
            ('sofa_bed'),
            ('crib'),
            ('air_mattress'),
            ('smoke_alarm'),
            ('fire_extinguisher'),
            ('security_guard'),
            ('cctv'),
            ('intercom_system'),
            ('safe'),
            ('hot_water'),
            ('private_bathroom'),
            ('shower_gel'),
            ('bathtub'),
            ('mosquito_net');

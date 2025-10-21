TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE customers CASCADE;
TRUNCATE TABLE admins CASCADE;

INSERT INTO users(id, first_name, last_name, email, password, phone, roles, created_at, updated_at)
VALUES('ADMIN001', 'Chinedu', 'Okonkwo', 'chinedu.okonkwo@gmail.com', 'hashed_password', '07012345678', 'ADMIN', NOW(), NOW()),
      ('CUSTOMER001', 'Adunni', 'Alao', 'adunni.alao@gmail.com', 'hashed_password', '08012345678', 'CUSTOMER', NOW(), NOW()),
      ('CUSTOMER002', 'Emeka', 'Eze', 'emeka.eze@company.com', 'hashed_password', '09012345678', 'CUSTOMER', NOW(), NOW());


INSERT INTO admins(id, admin_level, department)
VALUES('ADMIN001', 'Super Admin', 'Operations');


INSERT INTO customers(id, contact, address, gender)
VALUES('CUSTOMER001', '08012345678', '12 Allen Avenue', 'Female'),
      ('CUSTOMER002', '09012345678', '18 Vaughan street', 'Male');



-- insert into organizations (id, name, code, address, contact_email, contact_phone, verified, created_at) values
--     ('org123', 'Oluwaloni Services Ltd', 'OSL001', '156 Awolowo Way, Ikoyi, Lagos', 'info@oluwaloniservices.com', '0123456789', true, CURRENT_TIMESTAMP),
--     ('org124', 'Chibuzor & Sons Nig. Ltd', 'CSN001', '42 Okpara Avenue, Surulere, Lagos', 'info@chibuzorsons.com', '0134567890', true, CURRENT_TIMESTAMP);
--
-- insert into offerings (id, name, description, estimated_wait_time, duration, organization_id, created_at) values
--     ('off123', 'Bank Deposit Service', 'Cash deposit service for all banks', 30, 15, 'org123', CURRENT_TIMESTAMP),
--     ('off124', 'Bill Payment Service', 'Utility bill payment service', 45, 20, 'org123', CURRENT_TIMESTAMP),
--     ('off125', 'Document Printing', 'Document printing and binding service', 60, 30, 'org124', CURRENT_TIMESTAMP);
--
-- insert into queues (id, name, description, organization_id, created_at) values
--     ('q123', 'Main Service Queue', 'General service queue for walk-in customers', 'org123', CURRENT_TIMESTAMP),
--     ('q124', 'Priority Queue', 'Priority service queue for premium customers', 'org123', CURRENT_TIMESTAMP),
--     ('q125', 'Printing Queue', 'Queue for printing services', 'org124', CURRENT_TIMESTAMP);
--
-- insert into appointments (id, user_id, offering_id, queue_id, appointment_date, status, queue_position, created_at) values
--     ('apt100', '12345', 'off123', 'q123', CURRENT_TIMESTAMP, 'CONFIRMED', 1, CURRENT_TIMESTAMP),
--     ('apt101', '12346', 'off124', 'q124', CURRENT_TIMESTAMP, 'CONFIRMED', 2, CURRENT_TIMESTAMP),
--     ('apt102', '12345', 'off125', 'q125', CURRENT_TIMESTAMP, 'PENDING', 3, CURRENT_TIMESTAMP);
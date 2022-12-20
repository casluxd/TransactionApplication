INSERT INTO payment_method (name, processing_fee) VALUES ('credit', 5);
INSERT INTO payment_method (name, processing_fee) VALUES ('debit', 3);

INSERT INTO status_payable (name) VALUES ('paid');
INSERT INTO status_payable (name) VALUES ('waiting_funds');
USE market_db;

/*CREATE TABLE employee (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          surname VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL
);*/

-- Insert initial data
INSERT INTO employee (name, surname, email) VALUES
                                       ('Andrei', 'Rabau', 'andrei.rabau@student.uj.edu.pl'),
                                       ('Andrii', 'Pokora', 'and.pokora@student.uj.edu.pl'),
                                       ('Dima', 'Polishchuk', 'dimoon@gmail.com');
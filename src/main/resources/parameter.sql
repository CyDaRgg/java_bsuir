DROP TABLE IF EXISTS request_parameters;
DROP TABLE IF EXISTS average_median;


CREATE TABLE request_parameters(
    parameters_id SERIAL PRIMARY KEY,

    first_param varchar(20) NOT NULL,
    second_param varchar(20) NOT NULL,
    third_param varchar(20) NOT NULL,
    fourth_param varchar(20) NOT NULL
);

CREATE TABLE average_median(
    average_median_id SERIAL PRIMARY KEY,

    average REAL NOT NULL,
    median REAL NOT NULL
);


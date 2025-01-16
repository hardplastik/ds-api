ALTER TABLE user_program
    ADD COLUMN name VARCHAR(200);

ALTER TABLE user_program
    RENAME COLUMN program_status TO is_started;
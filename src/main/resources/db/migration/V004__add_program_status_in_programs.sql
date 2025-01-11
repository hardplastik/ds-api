ALTER TABLE user_program
    ADD COLUMN program_status BOOLEAN NOT NULL;

ALTER TABLE program_session
    ADD COLUMN ps_status BOOLEAN NOT NULL;

ALTER TABLE program_session_exercise
    ADD COLUMN pse_status BOOLEAN NOT NULL;

ALTER TABLE program_session_exercise_set
    ADD COLUMN pses_status BOOLEAN NOT NULL;

ALTER TABLE program_session 
    ALTER COLUMN start_datetime DROP NOT NULL;

ALTER TABLE program_session 
    ALTER COLUMN end_datetime DROP NOT NULL;

ALTER TABLE user_program
    ADD COLUMN weeks SMALLINT;

ALTER TABLE user_program
    ADD COLUMN sessions_per_week SMALLINT;

ALTER TABLE user_program
    ADD COLUMN is_completed BOOLEAN;

ALTER TABLE program_template
    ADD COLUMN weeks SMALLINT;

ALTER TABLE program_template
    ADD COLUMN sessions_per_week SMALLINT;

ALTER TABLE program_session
    RENAME COLUMN ps_status TO is_completed;

ALTER TABLE program_session_exercise
    RENAME COLUMN pse_status TO is_completed;

ALTER TABLE program_session_exercise_set
    RENAME COLUMN pses_status TO is_completed;

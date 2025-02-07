ALTER TABLE user_program ADD COLUMN phase VARCHAR(50);
ALTER TABLE user_program ADD COLUMN goal VARCHAR(50);
ALTER TABLE user_program ADD COLUMN methodology VARCHAR(50);
ALTER TABLE user_program ADD COLUMN intensity VARCHAR(20);
ALTER TABLE user_program ADD COLUMN high_series INT;
ALTER TABLE user_program ADD COLUMN low_series INT;
ALTER TABLE user_program ADD COLUMN min_reps INT;
ALTER TABLE user_program ADD COLUMN max_reps INT;
ALTER TABLE user_program ADD COLUMN rir_min INT;
ALTER TABLE user_program ADD COLUMN rir_max INT;


ALTER TABLE program_session_exercise_set ADD COLUMN min_reps INT;
ALTER TABLE program_session_exercise_set ADD COLUMN max_reps INT;
ALTER TABLE program_session_exercise_set ADD COLUMN rir_min INT;
ALTER TABLE program_session_exercise_set ADD COLUMN rir_max INT;

ALTER TABLE program_session_template_exercise_set ADD COLUMN min_reps INT;
ALTER TABLE program_session_template_exercise_set ADD COLUMN max_reps INT;
ALTER TABLE program_session_template_exercise_set ADD COLUMN rir_min INT;
ALTER TABLE program_session_template_exercise_set ADD COLUMN rir_max INT;

ALTER TABLE program_session_exercise_set DROP COLUMN target_reps;
ALTER TABLE program_session_template_exercise_set DROP COLUMN target_reps;
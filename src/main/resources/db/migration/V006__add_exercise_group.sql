CREATE TABLE cat_muscle_group (
    muscle_group_id UUID CONSTRAINT pk_cat_muscle_group PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE cat_exercise_muscle_group (
    cat_exercise_muscle_group_id UUID CONSTRAINT pk_cat_exercise_muscle_group PRIMARY KEY,
    muscle_group_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    is_principal_group BOOLEAN NOT NULL,
    CONSTRAINT cat_exercise_muscle_group_fk01 FOREIGN KEY (muscle_group_id) REFERENCES cat_muscle_group(muscle_group_id),
    CONSTRAINT cat_exercise_muscle_group_fk02 FOREIGN KEY (exercise_id) REFERENCES cat_exercise(exercise_id)
);
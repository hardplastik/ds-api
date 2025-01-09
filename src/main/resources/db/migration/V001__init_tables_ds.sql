CREATE TABLE "user" (
    user_id UUID CONSTRAINT pk_user PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password_salt VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    deleted BOOLEAN NOT NULL
);

CREATE TABLE cat_roles (
    role_id UUID CONSTRAINT pk_cat_roles PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE user_role (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_fk01 FOREIGN KEY (user_id) REFERENCES "user"(user_id),
    CONSTRAINT user_role_fk02 FOREIGN KEY (role_id) REFERENCES cat_roles(role_id)
);

CREATE TABLE user_program (
    user_program_id UUID CONSTRAINT pk_user_program PRIMARY KEY,
    user_id UUID NOT NULL,
    enroll_datetime TIMESTAMP NOT NULL,
    CONSTRAINT user_program_fk01 FOREIGN KEY (user_id) REFERENCES "user"(user_id)
);

CREATE TABLE program_template (
    program_template_id UUID CONSTRAINT pk_program_template PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE program_session (
    program_session_id UUID CONSTRAINT pk_program_session PRIMARY KEY,
    program_id UUID NOT NULL,
    week_number SMALLINT NOT NULL,
    week_day SMALLINT NOT NULL,
    start_datetime TIMESTAMP NOT NULL,
    end_datetime TIMESTAMP NOT NULL,
    CONSTRAINT program_session_fk01 FOREIGN KEY (program_id) REFERENCES user_program(user_program_id)
);

CREATE TABLE program_session_template (
    program_session_template_id UUID CONSTRAINT pk_program_session_template PRIMARY KEY,
    program_template_id UUID NOT NULL,
    week_number SMALLINT NOT NULL,
    week_day SMALLINT NOT NULL,
    CONSTRAINT program_session_template_fk01 FOREIGN KEY (program_template_id) REFERENCES program_template(program_template_id)
);

CREATE TABLE cat_exercise (
    exercise_id UUID CONSTRAINT pk_cat_exercise PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    media_url VARCHAR(255)
);

CREATE TABLE program_session_exercise (
    program_session_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    order_number SMALLINT NOT NULL,
    notes VARCHAR(200),
    CONSTRAINT pk_program_session_exercise PRIMARY KEY (program_session_id, exercise_id),
    CONSTRAINT uq_program_session_id UNIQUE (program_session_id),
    CONSTRAINT program_session_exercise_fk01 FOREIGN KEY (program_session_id) REFERENCES program_session(program_session_id),
    CONSTRAINT program_session_exercise_fk02 FOREIGN KEY (exercise_id) REFERENCES cat_exercise(exercise_id)
);

CREATE TABLE exercise_set (
    exercise_set_id UUID CONSTRAINT pk_exercise_set PRIMARY KEY,
    program_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    reps SMALLINT,
    target_reps VARCHAR(20),
    weight FLOAT,
    target_weight FLOAT,
    rpe SMALLINT,
    unit CHAR(1),
    CONSTRAINT exercise_set_fk01 FOREIGN KEY (program_id) REFERENCES program_session_exercise(program_session_id),
    CONSTRAINT exercise_set_fk02 FOREIGN KEY (exercise_id) REFERENCES cat_exercise(exercise_id)
);

CREATE TABLE pst_exercise (
    program_session_template_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    order_number SMALLINT NOT NULL,
    notes VARCHAR(200),
    CONSTRAINT pk_pst_exercise PRIMARY KEY (program_session_template_id, exercise_id),
    CONSTRAINT uq_pst_exercise_id UNIQUE (program_session_template_id),
    CONSTRAINT pst_exercise_fk01 FOREIGN KEY (program_session_template_id) REFERENCES program_session_template(program_session_template_id),
    CONSTRAINT pst_exercise_fk02 FOREIGN KEY (exercise_id) REFERENCES cat_exercise(exercise_id)
);

CREATE TABLE pst_exercise_set (
    set_id UUID NOT NULL,
    pst_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    target_reps VARCHAR(20),
    target_weight FLOAT,
    rpe SMALLINT,
    unit CHAR(1),
    CONSTRAINT pk_pst_exercise_set PRIMARY KEY (set_id, pst_id, exercise_id),
    CONSTRAINT pst_exercise_set_fk01 FOREIGN KEY (pst_id) REFERENCES pst_exercise(program_session_template_id),
    CONSTRAINT pst_exercise_set_fk02 FOREIGN KEY (exercise_id) REFERENCES cat_exercise(exercise_id)
);
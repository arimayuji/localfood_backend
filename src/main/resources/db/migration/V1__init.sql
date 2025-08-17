BEGIN;

CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS citext;  

DO $$ BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'place_category') THEN
    CREATE TYPE place_category AS ENUM ('RESTAURANT','STREET_FOOD','MARKET','CAFE','BAR');
  END IF;
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'price_level') THEN
    CREATE TYPE price_level AS ENUM ('CHEAP','MODERATE','EXPENSIVE');
  END IF;
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'review_audience') THEN
    CREATE TYPE review_audience AS ENUM ('GENERAL','FOREIGNERS');
  END IF;
END $$;

CREATE TABLE IF NOT EXISTS users (
  id            uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name          varchar(120)      NOT NULL,
  email         citext            NOT NULL,
  password_hash text              NOT NULL,
  locale        varchar(10),
  created_at    timestamptz       NOT NULL DEFAULT now(),
  updated_at    timestamptz       NOT NULL DEFAULT now(),
  CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS places (
  id            uuid            PRIMARY KEY DEFAULT gen_random_uuid(),
  name          varchar(160)    NOT NULL,
  description   text,
  city          varchar(120),
  country       varchar(2),
  location      geography(Point, 4326) NOT NULL,
  category      place_category  NOT NULL,
  price_level   price_level,
  is_local_owned boolean,
  phone         varchar(32),
  website       varchar(255),
  created_by    uuid REFERENCES users(id) ON DELETE SET NULL,
  created_at    timestamptz     NOT NULL DEFAULT now(),
  updated_at    timestamptz     NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_places_location_gix ON places USING GIST (location);
CREATE INDEX IF NOT EXISTS idx_places_category ON places (category);
CREATE INDEX IF NOT EXISTS idx_places_city ON places (city);

CREATE TABLE IF NOT EXISTS reviews (
  id            uuid            PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id       uuid            NOT NULL REFERENCES users(id)  ON DELETE CASCADE,
  place_id      uuid            NOT NULL REFERENCES places(id) ON DELETE CASCADE,
  rating        smallint        NOT NULL CHECK (rating BETWEEN 1 AND 5),
  comment       text,
  tip_for_foreigners text,
  audience      review_audience NOT NULL DEFAULT 'GENERAL',
  created_at    timestamptz     NOT NULL DEFAULT now(),
  updated_at    timestamptz     NOT NULL DEFAULT now(),
  CONSTRAINT uq_review_user_place UNIQUE (user_id, place_id)
);

CREATE INDEX IF NOT EXISTS idx_reviews_place_id ON reviews (place_id);
CREATE INDEX IF NOT EXISTS idx_reviews_user_id  ON reviews (user_id);

CREATE TABLE IF NOT EXISTS favorite_places (
  user_id     uuid        NOT NULL REFERENCES users(id)  ON DELETE CASCADE,
  place_id    uuid        NOT NULL REFERENCES places(id) ON DELETE CASCADE,
  created_at  timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (user_id, place_id)
);

COMMIT;

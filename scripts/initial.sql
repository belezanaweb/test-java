CREATE SCHEMA IF NOT EXISTS simios AUTHORIZATION simios_user_adm;

create table if not exists simios.dna
(
  idt_dna_sequence   varchar,
  ind_simian_type  varchar,
  dat_creation     timestamp NOT NULL,
  dat_update       timestamp NOT NULL
);

ALTER TABLE simios.dna
  ADD CONSTRAINT dna_pk PRIMARY KEY (idt_dna_sequence);

CREATE INDEX dna_idx01 ON simios.dna (ind_simian_type);
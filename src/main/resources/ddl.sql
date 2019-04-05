 CREATE SCHEMA p2ptransfer
    AUTHORIZATION postgres;
	
GRANT ALL ON SCHEMA p2ptransfer TO postgres;

CREATE TABLE p2ptransfer."p2ptransfer"
(
    "id" bigint NOT NULL,
    "transaction_code" character varying(50) NOT NULL,
    "original_account" bigint NOT NULL,
	"destination_account" bigint NOT NULL,
	"amount" double precision NOT NULL,
	"date" date NOT NULL,
	"description" character varying(150) NOT NULL,
	"id_adjustment" bigint NOT NULL,
	"id_issuer" bigint NOT NULL,
	"tp_adjustment" character varying(1) NOT NULL,
	"transaction_tariff_code" character varying(50) NOT NULL,
    PRIMARY KEY ("id")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE p2ptransfer."p2ptransfer"
    OWNER to postgres;
COMMENT ON TABLE p2ptransfer."p2ptransfer"
    IS 'p2ptransfer Tables';
	
CREATE SEQUENCE p2ptransfer.p2ptransfer_id_seq;
ALTER TABLE p2ptransfer.p2ptransfer ALTER COLUMN "id" SET DEFAULT nextval('p2ptransfer.p2ptransfer_id_seq'::regclass);
ALTER SEQUENCE p2ptransfer.p2ptransfer_id_seq OWNED BY p2ptransfer.p2ptransfer.id;
ALTER TABLE p2ptransfer.p2ptransfer ALTER COLUMN "date" TYPE timestamp(4) without time zone ;
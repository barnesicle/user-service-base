CREATE KEYSPACE account
WITH durable_writes = true
AND replication = {
	'class' : 'SimpleStrategy',
	'replication_factor' : 1
};

CREATE TABLE users.account_by_username (
	username text,
	email text,
	password text,
	verified boolean,
	created_timestamp timestamp,
	first_name text,
	last_name text,
	roles set<text>,
	enabled boolean,
	valid boolean,
	failed_login_attempts int,
	password_reset_code text,
	verify_account_code text,
	PRIMARY KEY (username)
);
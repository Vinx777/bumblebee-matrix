create table if not exists `organizations`
(
    id bigint unsigned auto_increment primary key,
    name varchar(255) not null comment '企業名',
    representative_name varchar(255) not null comment '代表者名',
    phone_number varchar(20) not null comment '電話番号',
    postal_code char(7) not null comment '郵便番号',
    address varchar(255) not null comment '住所',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
) comment '企業';

create table if not exists `users`
(
    id bigint unsigned auto_increment primary key,
    organization_id bigint unsigned comment '企業 ID',
    name varchar(255) not null comment '氏名',
    email varchar(255) not null comment 'メールアドレス',
    password varchar(255) not null comment 'パスワード',
    verified bool not null default false comment '認証済みフラグ。trueの場合は認証済み。',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
) comment 'ユーザー';

create table if not exists `payments`
(
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null comment 'ユーザー ID',
    amount int not null comment '金額',
    fee int not null comment '手数料',
    fee_rate decimal(5, 5) not null comment '手数料率',
    tax_rate decimal(5, 5) not null comment '消費税率',
    billing_amount int not null comment '請求金額',
    transfer_date date not null comment '振込日',
    uploaded_date date not null comment 'アップロード日',
    status int not null comment 'ステータス, 0: 未処理, 1: 処理中, 2: 処理済み, 99: エラー',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
);

create table if not exists `payment_bank_accounts`
(
    id bigint unsigned auto_increment primary key,
    payment_id bigint unsigned not null,
    bank_code varchar(4) not null,
    bank_name varchar(64) not null,
    branch_code varchar(3) not null,
    branch_name varchar(64) not null,
    account_type int not null,
    account_number varchar(8) not null,
    account_holder varchar(128) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    unique key uk_payment_id(payment_id)
);

create table if not exists `banks`
(
    id bigint unsigned auto_increment primary key,
    bank_code varchar(4) not null,
    bank_name varchar(64) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    unique key uk_bank_code(bank_code)
);

create table if not exists `bank_branches`
(
    id bigint unsigned auto_increment primary key,
    bank_code varchar(4) not null,
    branch_code varchar(3) not null,
    branch_name varchar(64) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    unique key uk_bank_code_branch_code (bank_code, branch_code)
);

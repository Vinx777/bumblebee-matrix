# はじめに

This is a backend project written by java/scala and springboot. 

## タスク一覧

1.  REST API
2. テストコード

## 作っていただきたい API 

1. user_id を指定して users テーブルから取得する API（参照系）
2.  user の情報を送信して users テーブルに登録する API（登録系）
3. users テーブルの情報を指定した件数分、かつ、ページングを考慮して取得する API（参照系）
4. payments テーブルを期間指定で取得し、amount の合計を取得する API（参照系）
5.  payments テーブルにデータを登録する api を以下の条件を満たした上で作成してください（登録系）
    1. fee_rate は 4%、tax_rate は10% で計算し、billing_amount に計算した金額を設定する
       1. 例) amount が10,000の場合 billing_amount は 10,440 になる
    2. status は 0 で登録する
    3. payments 登録時に payment_bank_accounts も同時にデータを登録する
6. jwt による認証処理を作成し、1-5 で作成した api のいずれかに認証処理を追加する
    1. 認証処理に必要なライブラリは自由に追加していただいて構いません

## Swagger endpoint

http://localhost:8080/swagger-ui/index.html

## TODO
1. Jwtによろ認証機能を追加する
2. サービステストを追加する
# Spring Boot Todo アプリケーション

このプロジェクトは、Spring Boot、H2データベース、MyBatis、Spring Securityを使用した簡単なTodo管理アプリケーションです。ユーザー認証・認可機能を備え、ユーザーごとのタスク管理が可能です。

## 機能

- ユーザー認証（ログイン/ログアウト）
- ユーザー登録
- Todoタスクの作成、表示、完了、削除
- ユーザー別のTodo管理
- 管理者ダッシュボード
- アクセス権限の管理

## 技術スタック

- **フレームワーク**: Spring Boot 3.x
- **データベース**: H2 Database (ファイルベース)
- **OR/Mマッパー**: MyBatis
- **セキュリティ**: Spring Security
- **テンプレートエンジン**: Thymeleaf
- **ビルドツール**: Maven

## プロジェクト構成

```
demo_caser/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo_caser/
│   │   │   ├── config/         # 設定クラス
│   │   │   ├── controller/     # コントローラー
│   │   │   ├── model/          # モデルクラス
│   │   │   ├── repository/     # データアクセス
│   │   │   ├── service/        # ビジネスロジック
│   │   │   └── DemoCaserApplication.java
│   │   └── resources/
│   │       ├── static/         # 静的リソース
│   │       ├── templates/      # Thymeleafテンプレート
│   │       ├── application.properties  # アプリケーション設定
│   │       ├── schema.sql      # DBスキーマ定義
│   │       └── data.sql        # 初期データ
│   └── test/                   # テストコード
└── pom.xml                     # Mavenプロジェクト定義
```

## データベース設計

### ユーザーテーブル (users)
- id (BIGINT): プライマリキー
- username (VARCHAR): ユーザー名 (一意)
- password (VARCHAR): パスワード (BCryptでハッシュ化)
- role (VARCHAR): ユーザーロール (ADMIN/USER)
- enabled (BOOLEAN): アカウント有効フラグ

### Todoテーブル (todos)
- id (BIGINT): プライマリキー
- title (VARCHAR): タスクのタイトル
- completed (BOOLEAN): 完了フラグ
- created_at (TIMESTAMP): 作成日時
- user_id (BIGINT): ユーザーID (外部キー)

## セキュリティ設定

- 認証が必要なページ: `/todos`, `/admin/*`
- 認証不要のページ: `/login`, `/register`, `/css/*`, `/js/*`
- 管理者専用ページ: `/admin/*`
- 一般ユーザー/管理者アクセス可能: `/todos/*`, `/api/todos/*`

## アクセスURL

- `/` - トップページ (Todoリストにリダイレクト)
- `/login` - ログインページ
- `/register` - ユーザー登録ページ
- `/todos` - Todoリスト
- `/admin/dashboard` - 管理者ダッシュボード
- `/admin/todos` - 全ユーザーのTodo一覧 (管理者用)
- `/h2-console` - H2データベースコンソール

## 初期ユーザー

| ユーザー名 | パスワード | 権限 |
|---------|---------|------|
| admin   | password | ADMIN |
| user    | password | USER  |

## SQLログ

発行されるSQLクエリは `logs/sql.log` ファイルに記録されます。デバッグやパフォーマンス分析に活用できます。

## 使用方法

1. プロジェクトをクローン/ダウンロード
2. Maven依存関係をインストール: `mvn install`
3. アプリケーションを実行: `mvn spring-boot:run`
4. ブラウザで `http://localhost:8080` にアクセス

## 注意事項

- このアプリケーションはデモ用であり、本番環境での使用は推奨されません
- H2データベースは `./data/todo_db` ファイルに保存されます
- アプリケーションを再起動してもデータは保持されます

## カスタマイズポイント

- `application.properties` でデータベース設定やログ出力などをカスタマイズ可能
- `SecurityConfig.java` でセキュリティルールをカスタマイズ可能
- Thymeleafテンプレートを編集してUIをカスタマイズ可能

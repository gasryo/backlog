# Backlog簡易課題一覧

## 機能
* keyword検索
* ページャー
* 表示件数の変更


## 本プロジェクトに必要なもの
* Java(OpenJDK) 1.8.0+

## 起動

以下の環境変数を設定

* BACKLOG_API_KEY
* BACKLOG_SPACE_ID
* BACKLOG_PROJECT_NAME


起動

```
$ ./sbt.sh web/run
```

URL

```
http://localhost:9000/issues/
```

## 構成
ヘキサゴナルアーキテクチャベース

```
├─ core
│   └── src
│       └── main
│           └── scala
│               └── com
│                   └── example
│                       └── backlog
│                           ├── application アプリケーション層
│                           ├── common BacklogAPIの設定
│                           ├── domain ドメイン層
│                           └── infrastructure インフラ層（BacklogAPIの処理)
└── web
    └── app
        └── com
            └── example
                └── backlog
                    └── infrastructure
                        └── play play frameworkによるwebアプリ
```

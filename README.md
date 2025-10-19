# コミサポライブ Android アプリ

コミサポライブは、ライバー情報の検索やレビュー投稿、コラボ可能ライバー紹介などを行う情報アプリです。本リポジトリは Android 版のソースコードを管理しています。Jetpack Compose を用いた UI と、Firebase、Retrofit 等を組み合わせた実装になっています。

## 主な機能

- **ホーム画面**: 新着ライバー・記事・コラボ可能ライバーを表示。記事タップでアプリ内 WebView が開きます。
- **検索画面**: ライバー名やカテゴリ、プラットフォーム等の条件で絞り込みが可能。検索履歴も保存します。
- **カテゴリ画面**: ジャンル別・配信アプリ別の 2 タブ構成でライバーを閲覧できます。
- **マイページ**: ログイン WebView へ遷移するシンプルな画面。
- **レビュー投稿**: Firebase Messaging を利用し、口コミや通知機能を提供します。
- **広告表示**: Google AdMob を組み込み、バナー広告を表示します。

## 開発環境

- Android Studio Hedgehog / Jellyfish 以降
- Gradle 7.4.2 + Android Gradle Plugin 7.4.2
- Kotlin 1.8.22
- Jetpack Compose (BOM 2022.10.00)
- Firebase Messaging, Google AdMob

## ビルド・実行

```bash
./gradlew clean
./gradlew app:assembleDebug
```

Android Studio からは通常どおり Run/Debug を実行してください。広告表示や Push 通知を試すには、Firebase プロジェクトの設定と `google-services.json` の配置が必要です。

### 初期設定

1. `local.properties` をルート直下に作成し、`sdk.dir=/path/to/android/sdk` を設定します（Git 管理から除外されています）。
2. Firebase 連携を行う場合は、`app/google-services.json` を同じく手動で配置してください。公開リポジトリには含めないでください。
3. 署名付きビルドが必要な場合は `signingKey.keystore` などのキーファイルを個別に受け取り、`app/build.gradle` の該当設定を有効化してください。

### リリースビルド

署名情報 (`signingKey.keystore` など) は Git 管理から除外することを推奨します。リリース用 AAB/APK を生成するには以下を実行してください。

```bash
./gradlew app:bundleRelease
# or
./gradlew app:assembleRelease
```

生成された成果物は `app/build/outputs/` 以下に配置されます。Google Play Console へアップロードする際は、`com.google.android.gms.permission.AD_ID` が含まれていることを確認してください。

## API

- ベース URL: `https://liver-scraper-main.liver-scraper-detailsapi.workers.dev`
- 主なエンドポイント
  - `/api/livers` : ライバー一覧取得
  - `/api/reviews/` : 口コミ一覧・投稿

## ディレクトリ構成

```
app/
 ├── src/main/
 │   ├── java/comisapolive/app/...   # Kotlin ソースコード（UI・ViewModel・DI・Repository 等）
 │   ├── res/                        # レイアウト、画像リソース
 │   └── AndroidManifest.xml         # マニフェスト
 ├── build.gradle                    # モジュール設定
gradle/                               # Gradle ラッパー
build.gradle                          # ルート設定
settings.gradle                       # モジュール定義
```

## 注意事項

- リリース用の APK/AAB・署名キーストアは Git 管理から外すことを推奨します。
- `.gitignore` によりビルド生成物・機密ファイルは除外済みです。追加のシークレットがある場合は同様に除外設定を行ってください。
- iOS 由来の画像資産が多数含まれるため、未使用リソースの整理や命名統一を適宜行ってください。
- Firebase や AdMob のキーは開発用と本番用で分け、セキュリティに注意してください。

## ライセンス

本リポジトリの著作権・ライセンス形態は管理者の指示に従ってください。README には公開していません。

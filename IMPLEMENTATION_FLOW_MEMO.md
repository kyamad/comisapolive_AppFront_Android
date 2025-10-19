# iOS同等レベル実装フロー - メモ

## 🚀 AndroidアプリをiOS同等レベルにする実装フロー

### 📊 Phase 1: データ基盤の完全リニューアル
**優先度: CRITICAL | 所要時間: 2-3時間**

#### 1.1 新API対応（45分）
- `LiverAPI.kt`を`LiverAPI.swift`同等に更新
- `LiverDetailsData`クラス追加
- `details`オブジェクト完全対応
- 重複除去ロジック実装

#### 1.2 データモデル拡張（30分）
- `ProfileInfo`, `Schedule`, `StreamingUrl`クラス追加
- `fullImageURL`計算プロパティ実装
- `channelUrl`計算プロパティ実装
- `collaborationStatus`判定ロジック

#### 1.3 APIクライアント強化（45分）
- `newLivers`プロパティ追加（上位5件）
- `colaboLivers`プロパティ追加（collaborationStatus="OK"）
- ランダム化・フィルタリングロジック
- エラーハンドリング強化

#### 1.4 動作確認
- 新APIでのデータ取得確認
- データ構造の正常性確認

---

### 📱 Phase 2: 口コミシステム完全実装
**優先度: CRITICAL | 所要時間: 2-3時間**

#### 2.1 口コミAPIクライアント（60分）
- `ReviewAPIClient.kt`作成
- 口コミ取得・投稿・統計API実装
- レート制限対応（429エラー）
- UserDefaultsManager相当の実装（SharedPreferences）

#### 2.2 口コミUI（60分）
- `ReviewsView`コンポーネント作成
- `ReviewSubmissionView`モーダル作成
- 星評価UI（5段階）
- 1000文字制限TextEditor

#### 2.3 詳細画面統合（30分）
- `LiverDetailsView`に口コミ表示統合
- 投稿済み判定表示
- 口コミ統計（平均評価・件数）表示

#### 2.4 動作確認
- 口コミ投稿・表示の動作確認
- レート制限の正常動作確認

---

### 🎨 Phase 3: UI基盤とレイアウト調整
**優先度: HIGH | 所要時間: 2-3時間**

#### 3.1 メイン画面構造調整（45分）
- `MainActivity.kt`をiOS `CustomTabView`同等に更新
- 4画面タブ構造の確認・調整
- タブアイコン・名称をiOS同等に統一

#### 3.2 ホーム画面基盤（60分）
- `HomeScreen.kt`をiOS `HomeView`同等に更新
- 広告エリア配置調整
- セクション分け（New・記事・コラボOK）

#### 3.3 カテゴリ画面2タブ化（45分）
- `ContentScreen.kt`を2タブ構造に変更
- ジャンル検索タブ
- プラットフォーム検索タブ

#### 3.4 検索画面基盤（30分）
- `SearchScreen.kt`基本レイアウト調整
- 検索バー・履歴表示エリア確保

---

### 🎪 Phase 4: 高度なUIコンポーネント実装
**優先度: HIGH | 所要時間: 3-4時間**

#### 4.1 NewLiverCarousel（90分）
- 円形プロフィール画像（180dp）
- 背景点々パターン（`WithBackground`相当）
- 横スクロールカルーセル
- タップ時の詳細表示

#### 4.2 ColaboLiverCarousel（120分）
- カスタム角丸形状（`CustomRoundedCorners`相当）
- 3D影効果・立体感演出
- カテゴリタグ（`CategoryTagShape`相当）
- スクロール可能なカテゴリ表示

#### 4.3 LiverDetailsModal（60分）
- iOSスタイルのモーダル表示
- `presentationDetents`相当の実装
- 角丸・影効果
- 口コミセクション統合

---

### 🔍 Phase 5: 検索・プラットフォーム機能強化
**優先度: MEDIUM | 所要時間: 2-3時間**

#### 5.1 高度な検索機能（90分）
- リアルタイム検索（名前・カテゴリ・性別・コメント・プラットフォーム）
- 検索履歴管理（最大10件、SharedPreferences）
- キーボードフォーカス制御
- 検索結果カード（`SearchResultCard`）

#### 5.2 プラットフォーム選択UI（60分）
- プラットフォーム画像マッピング
- 横スクロール選択UI
- 動的プラットフォーム取得（`schedules`から）
- 選択状態のビジュアルフィードバック

#### 5.3 ジャンル画面調整（30分）
- iOSスタイルのジャンルカード
- `GenreCardView`相当のレイアウト

---

### ✨ Phase 6: 細部の最適化・ポリッシュ
**優先度: LOW | 所要時間: 1-2時間**

#### 6.1 アニメーション・エフェクト（45分）
- タブ切り替えアニメーション
- カルーセルアニメーション
- モーダル表示アニメーション

#### 6.2 色・フォント統一（30分）
- iOSと完全同一の色定義
- フォントサイズ・ウェイト統一
- 角丸・影効果の調整

#### 6.3 最終動作確認（30分）
- 全機能の動作確認
- iOS画面との詳細比較
- パフォーマンス確認

---

## 🎯 実装スケジュール

### Day 1（合計: 4-6時間）
- Phase 1: データ基盤完全リニューアル
- Phase 2: 口コミシステム完全実装

### Day 2（合計: 5-7時間）
- Phase 3: UI基盤とレイアウト調整
- Phase 4: 高度なUIコンポーネント実装

### Day 3（合計: 3-5時間）
- Phase 5: 検索・プラットフォーム機能強化
- Phase 6: 細部の最適化・ポリッシュ

## 📋 各Phaseの成果物

- **Phase 1完了時**: 新API・データ構造完全対応
- **Phase 2完了時**: 口コミシステム完全動作
- **Phase 3完了時**: iOS同等の画面構造
- **Phase 4完了時**: iOS同等のリッチUI
- **Phase 5完了時**: iOS同等の検索・プラットフォーム機能
- **Phase 6完了時**: iOS完全同等のAndroidアプリ

## 🔧 実装状況追跡

- [ ] Phase 1: データ基盤の完全リニューアル
- [ ] Phase 2: 口コミシステム完全実装
- [ ] Phase 3: UI基盤とレイアウト調整
- [ ] Phase 4: 高度なUIコンポーネント実装
- [ ] Phase 5: 検索・プラットフォーム機能強化
- [ ] Phase 6: 細部の最適化・ポリッシュ

---

**iOS参考URL**: `https://liver-scraper-main.liver-scraper-detailsapi.workers.dev/api/livers`
**口コミAPI**: `https://liver-scraper-main.liver-scraper-detailsapi.workers.dev/api/reviews/`
